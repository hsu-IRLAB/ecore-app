package com.hsu_irlab.data.remote.dataSource

import com.google.gson.JsonObject
import com.hsu_irlab.data.remote.dto.UserChangeNameDto
import com.hsu_irlab.data.remote.dto.UserExistDto
import com.hsu_irlab.data.remote.dto.UserInfoDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query

interface UserDataSource {
    @GET("/user/info")
    suspend fun getUserInfo(
        @Query("user_id")
        user_id: Int
    ): UserInfoDto

    @GET("/user/name/exist")
    suspend fun getUserExist(
        @Query("name")
        name: String
    ) : UserExistDto

    @PUT("/user/nickname")
    suspend fun putChangeName(@Body query: JsonObject):Response<UserChangeNameDto>


}