package com.hsu_irlab.data.response.changename

import com.hsu_irlab.domain.model.DomainChangeName

data class ChangeNameResponse(
    val Data: String,
    val Message: String
)

fun ChangeNameResponse.toDomainChangeName() : DomainChangeName = DomainChangeName(
    Data,Message
)