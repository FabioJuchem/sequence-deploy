package com.fabiojuchem.sequenceeditor.domain

import org.springframework.data.annotation.Id
import java.util.UUID

abstract class EntityBase {

    @Id
    val id: UUID = UUID.randomUUID()
}