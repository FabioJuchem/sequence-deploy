package com.fabiojuchem.sequenceeditor.resource.rest

import com.fabiojuchem.sequenceeditor.domain.auth.user.dto.LoginDto
import com.fabiojuchem.sequenceeditor.domain.auth.user.dto.UserDetailsDto
import com.fabiojuchem.sequenceeditor.domain.auth.user.dto.UserDto
import com.fabiojuchem.sequenceeditor.domain.auth.user.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class AuthResource(
    val userService: UserService,
) {

    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    fun createUser(@RequestBody @Valid dto: UserDto) = userService.createUser(dto)
    //TODO verificar porque o @Valid não esta funcionando

    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    fun login(@RequestBody dto: LoginDto) = userService.login(dto)

    @CrossOrigin
    @GetMapping("/user")
    fun getUserData(): Mono<UserDetailsDto> {
        return userService.findUserByToken()
    }

}