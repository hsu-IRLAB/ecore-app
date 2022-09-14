package com.hsu_irlab.domain.repository

import com.hsu_irlab.domain.model.DomainUserInfo

interface UserRepository {
    suspend fun getUserInfo(user_id : Int): DomainUserInfo

}