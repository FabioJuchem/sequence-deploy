package com.fabiojuchem.sequenceeditor.domain.converter.converters

import com.fabiojuchem.sequenceeditor.domain.converter.Converter
import com.fabiojuchem.sequenceeditor.domain.converter.Input
import com.fabiojuchem.sequenceeditor.domain.converter.Output
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

class RnaToDnaConverter : Converter {

    override fun convert(input: Input): Mono<Output> {
        return input.toMono()
            .map { input.value.cleanString() }
            .map { it.replace("U", "T") }
            .map { Output(it) }
    }


}