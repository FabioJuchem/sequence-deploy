package com.fabiojuchem.sequenceeditor.domain.converter.decorators

import com.fabiojuchem.sequenceeditor.domain.converter.Output
import org.springframework.stereotype.Component

@Component
class LowercaseDecorator : ConverterDecorator {

    override val type: DecoratorType
        get() = DecoratorType.LOWERCASE

    override fun apply(output: Output): Output {
        output.value = output.value.toLowerCase()
        return output
    }

}