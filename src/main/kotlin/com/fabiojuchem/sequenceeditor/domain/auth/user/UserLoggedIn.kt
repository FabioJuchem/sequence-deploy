package com.fabiojuchem.sequenceeditor.domain.auth.user

import com.fabiojuchem.sequenceeditor.domain.auth.token.Token

data class UserLoggedIn(
        val token: Token
)