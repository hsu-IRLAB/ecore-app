package com.hsu_irlab.data.repository

import android.util.Log
import com.google.gson.JsonObject
import com.hsu_irlab.data.remote.dataSource.BadgeDataSource
import com.hsu_irlab.data.remote.dataSource.UserDataSource
import com.hsu_irlab.data.remote.dto.toDomainUserChangeName
import com.hsu_irlab.domain.model.DomainBadge
import com.hsu_irlab.domain.model.DomainUserChangeName
import com.hsu_irlab.domain.model.DomainUserExist
import com.hsu_irlab.domain.model.DomainUserInfo
import com.hsu_irlab.domain.repository.BadgeRepository
import com.hsu_irlab.domain.repository.UserRepository

class UserRepositoryImpl (
    private val api: UserDataSource
) : UserRepository {

    override suspend fun getUserInfo(user_id: Int): DomainUserInfo {
        val data = api.getUserInfo(user_id)
        return data.Data.toDomainUserInfo()
//        return data.body()?.Data?.toDomainUserInfo()?:DomainUserInfo(-1,-1,-1,-1,"","",-1)
    }

    override suspend fun getUserExist(name: String): DomainUserExist {
        val data = api.getUserExist(name)
        return data.toDomainUserExist()
    }

    override suspend fun putUserName(name: String): DomainUserChangeName {
        val jsonData: JsonObject = JsonObject().apply {
            addProperty("name",name) }
        val data = api.putChangeName(jsonData).body()
        return data?.toDomainUserChangeName() ?: DomainUserChangeName("","")
    }
}
