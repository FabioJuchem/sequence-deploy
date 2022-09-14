package com.fabiojuchem.sequenceeditor.domain.auth.user.service

import com.fabiojuchem.sequenceeditor.domain.auth.token.Token
import com.fabiojuchem.sequenceeditor.domain.auth.token.TokenBuilder
import com.fabiojuchem.sequenceeditor.domain.auth.token.repository.TokenRepository
import com.fabiojuchem.sequenceeditor.domain.auth.user.TokenDetails
import com.fabiojuchem.sequenceeditor.domain.auth.user.UserAccount
import com.fabiojuchem.sequenceeditor.domain.auth.user.dto.LoginDto
import com.fabiojuchem.sequenceeditor.domain.auth.user.dto.UserDetailsDto
import com.fabiojuchem.sequenceeditor.domain.auth.user.dto.UserDto
import com.fabiojuchem.sequenceeditor.domain.auth.user.repository.UserRepository
import com.fabiojuchem.authapi.infrastructure.exception.LoginInvalidException
import com.fabiojuchem.authapi.infrastructure.exception.UserSessionInvalidException
import com.fabiojuchem.sequenceeditor.infrastructure.rest.USER_LOGGED_IN
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UserService(
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun createUser(dto: UserDto): Mono<Void> {
        return userRepository.save(dto.toUser())
                .doOnSuccess { logger.info("m=createUser, username=${dto.username}") }
                .doOnError { logger.error("m=createUser, message=${it.message}") }
    }

    fun login(dto: LoginDto): Mono<String> {
          return findUserAccount(dto)
                 .flatMap { findToken(it!!) }
                 .flatMap {
                     val finalToken = it.token
                     finalToken.renew()
                     persistTokenData(it)
                             .thenReturn(finalToken.token)
                 }
                  .doOnSuccess { logger.info("m=login, message=Logged with success") }
                  .doOnError { logger.error("m=login, message=${it.message}") }
    }

    private fun findUserAccount(dto: LoginDto) =
            userRepository.findByUsernameAndPassword(dto.username, TokenBuilder.hashSecret(dto.password))
                    .switchIfEmpty(Mono.error(LoginInvalidException("user account is invalid")))

    private fun persistTokenData(token: TokenDetails) = if (token.isNew) tokenRepository.save(token.token) else tokenRepository.update(token.token)

    private fun findToken(it: UserAccount) =
            tokenRepository.findByUserId(it.id)
                    .map { token -> TokenDetails(token!!, it, false) }
                    .switchIfEmpty(Mono.just(TokenDetails(Token(TokenBuilder.build(), it.id), it, true)))

    fun findUserByToken(): Mono<UserDetailsDto> {
        return Mono.deferContextual {
                Mono.just(it.get<String>(USER_LOGGED_IN))
            }
            .map { it }
            .flatMap {
                tokenRepository.findByToken(it)
                    .flatMap { token ->
                        if (token != null) {
                            userRepository.findById(token.userId)
                                .map { user -> UserDetailsDto(user.name, user.email) }
                                .doOnSuccess { logger.info("m=findUserByToken, user=${it.name}") }
                        } else {
                            logger.error("m=findUserByToken, message=Failed to get userData")
                            throw UserSessionInvalidException()
                        }
                    }
            }
    }
}