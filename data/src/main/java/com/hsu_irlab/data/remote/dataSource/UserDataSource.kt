package com.hsu_irlab.data.remote.dataSource

import com.hsu_irlab.data.remote.dto.UserInfoDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserDataSource {
    @GET("/user/info")
    suspend fun getUserInfo(
        @Query("user_id")
        user_id: Int
    ): Response<UserInfoDto>

}