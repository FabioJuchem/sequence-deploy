package com.fabiojuchem.sequenceeditor.domain.converter.decorators

import com.fabiojuchem.sequenceeditor.domain.converter.Output
import org.springframework.stereotype.Component

@Component
class ReverseDecorator : ConverterDecorator {

    override val type: DecoratorType
        get() = DecoratorType.REVERSE

    override fun apply(output: Output): Output {
        output.value = output.value.reversed()
        return output
    }

}