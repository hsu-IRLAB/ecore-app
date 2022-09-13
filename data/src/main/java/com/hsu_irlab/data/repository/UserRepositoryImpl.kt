package com.hsu_irlab.data.repository

import com.hsu_irlab.data.remote.dataSource.BadgeDataSource
import com.hsu_irlab.data.remote.dataSource.UserDataSource
import com.hsu_irlab.domain.model.DomainBadge
import com.hsu_irlab.domain.model.DomainUserInfo
import com.hsu_irlab.domain.repository.BadgeRepository
import com.hsu_irlab.domain.repository.UserRepository

class UserRepositoryImpl (
    private val api: UserDataSource
) : UserRepository {

    override suspend fun getUserInfo(user_id: Int): DomainUserInfo {
        val data = api.getUserInfo(user_id)
        return data.body()?.Data?.toDomainUserInfo()?:DomainUserInfo(-1,-1,-1,-1,"","",-1)
    }
}