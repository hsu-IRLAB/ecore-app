package com.hsu_irlab.data.response.nameexist

import com.hsu_irlab.domain.model.DomainUserExist

data class UserExistResponse(
    val Data: String,
    val Message: String
)
fun UserExistResponse.toDomainUserExist() : DomainUserExist = DomainUserExist(
    Data,Message
)