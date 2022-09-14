package com.fabiojuchem.sequenceeditor.domain.converter.service

import com.fabiojuchem.sequenceeditor.domain.aminoacid.FetchType
import com.fabiojuchem.sequenceeditor.domain.converter.Input
import com.fabiojuchem.sequenceeditor.domain.converter.converters.AminoacidConverter
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class AminoacidConverterService(
   private val aminoacidConverter: AminoacidConverter
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun convertAminoacid(input: Input, direction: Boolean, options: FetchType): Mono<Triple<String, String, String>> {
       return aminoacidConverter.convert(input, direction, options)
    }

}