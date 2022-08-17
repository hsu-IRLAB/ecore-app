package com.hsu_irlab.domain.repository

import com.hsu_irlab.domain.model.DomainUserInfo

interface UserInfoRepository {
    suspend fun getUserInfo(user_id : Int): DomainUserInfo
}
