package com.hsu_irlab.data.network

import com.google.gson.JsonObject
import com.hsu_irlab.data.response.changename.ChangeNameResponse
import com.hsu_irlab.data.response.nameexist.UserExistResponse
import com.hsu_irlab.data.response.ranking.RakingResponse
import com.hsu_irlab.data.response.userinfo.UserInfoResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface ApiInterface {
    @GET("/ranking")
    suspend fun getRanking(): Response<RakingResponse>

    @GET("/user/info")
    suspend fun getUserInfo(
        @Query("user_id")
        user_id: Int
    ): Response<UserInfoResponse>

    @GET("/user/name/exist")
    suspend fun getUserExist(
        @Query("name")
        name: String
    ):Response<UserExistResponse>

    @PUT("/user/nickname")
    suspend fun putChangeName(@Body query: JsonObject ):Response<ChangeNameResponse>

}