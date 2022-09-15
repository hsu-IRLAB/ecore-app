package com.hsu_irlab.data.remote.dto

import com.hsu_irlab.domain.model.DomainUserChangeName

data class UserChangeNameDto(
    val Data: String,
    val Message: String
)
fun UserChangeNameDto.toDomainUserChangeName() : DomainUserChangeName = DomainUserChangeName(
    Data,Message
)

