package com.fabiojuchem.sequenceeditor.domain.auth.user

import com.fabiojuchem.sequenceeditor.domain.auth.token.Token

data class TokenDetails(
    val token: Token,
    val userAccount: UserAccount,
    val isNew: Boolean
)