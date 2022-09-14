package com.fabiojuchem.sequenceeditor.domain.converter

import com.fasterxml.jackson.annotation.JsonProperty

data class Output(
        @field:JsonProperty("value")
        var value: String
)