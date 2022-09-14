package com.fabiojuchem.sequenceeditor.domain.converter.decorators

import com.fabiojuchem.sequenceeditor.domain.converter.Output

interface ConverterDecorator {

    val type: DecoratorType

    fun apply(output: Output): Output
}