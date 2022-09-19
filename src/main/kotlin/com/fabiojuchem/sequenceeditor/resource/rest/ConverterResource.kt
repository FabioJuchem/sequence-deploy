package com.fabiojuchem.sequenceeditor.resource.rest


import com.fabiojuchem.sequenceeditor.domain.aminoacid.FetchType
import com.fabiojuchem.sequenceeditor.domain.converter.ConverterType
import com.fabiojuchem.sequenceeditor.domain.converter.Input
import com.fabiojuchem.sequenceeditor.domain.converter.decorators.DecoratorType
import com.fabiojuchem.sequenceeditor.domain.converter.service.AminoacidConverterService
import com.fabiojuchem.sequenceeditor.domain.converter.service.ConverterService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
class ConverterResource(
    private val converterService: ConverterService,
    private val aminoacidConverterService: AminoacidConverterService
) {

    @CrossOrigin
    @PostMapping("/convert/{type}", produces = [MediaType.APPLICATION_JSON_VALUE] )
    fun convert(
        @RequestBody input: Input,
        @PathVariable type: ConverterType,
        @RequestParam options: List<DecoratorType>
    ) =
        converterService.convert(input, type, options)

    @CrossOrigin
    @PostMapping("/convert/aminoacid", produces = [MediaType.APPLICATION_JSON_VALUE] )
    fun convertAminoacid(
        @RequestBody input: Input,
        @RequestParam direction: Boolean = false,
        @RequestParam options: FetchType = FetchType.SYMBOL
    ) = aminoacidConverterService.convertAminoacid(input, direction, options)


}