package com.hsu_irlab.data.remote.dataSourceImpl

import com.hsu_irlab.data.remote.dataSource.UserDataSource
import com.hsu_irlab.data.remote.dto.UserInfoDto
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val retrofit: Retrofit
): UserDataSource {
    override suspend fun getUserInfo(user_id:Int): Response<UserInfoDto> {
        return retrofit.create(UserDataSource::class.java).getUserInfo(user_id)
    }
}