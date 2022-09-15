package com.hsu_irlab.domain.repository

import com.hsu_irlab.domain.model.DomainUserChangeName
import com.hsu_irlab.domain.model.DomainUserExist
import com.hsu_irlab.domain.model.DomainUserInfo

interface UserRepository {
    suspend fun getUserInfo(user_id : Int): DomainUserInfo

    suspend fun getUserExist(name : String) : DomainUserExist

    suspend fun putUserName(name:String) : DomainUserChangeName
}