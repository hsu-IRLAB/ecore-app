package com.hsu_irlab.data.remote.dataSourceImpl

import com.google.gson.JsonObject
import com.hsu_irlab.data.remote.dataSource.UserDataSource
import com.hsu_irlab.data.remote.dto.UserChangeNameDto
import com.hsu_irlab.data.remote.dto.UserExistDto
import com.hsu_irlab.data.remote.dto.UserInfoDto
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val retrofit: Retrofit
): UserDataSource {
    override suspend fun getUserInfo(user_id:Int): UserInfoDto {
        return retrofit.create(UserDataSource::class.java).getUserInfo(user_id)
    }

    override suspend fun getUserExist(name: String): UserExistDto {
        return retrofit.create(UserDataSource::class.java).getUserExist(name)
    }

    override suspend fun putChangeName(query: JsonObject): Response<UserChangeNameDto> {
        return retrofit.create(UserDataSource::class.java).putChangeName(query)
    }

}