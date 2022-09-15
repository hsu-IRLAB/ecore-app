package com.hsu_irlab.data.remote.dto

import com.hsu_irlab.domain.model.DomainUserExist
import com.hsu_irlab.domain.model.DomainUserInfo

data class UserExistDto(
    val Data: String,
    val Message: String
){
    fun toDomainUserExist(): DomainUserExist{
        return DomainUserExist(data = Data)
    }
}
