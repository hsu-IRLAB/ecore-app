package com.hsu_irlab.data.network

import com.hsu_irlab.data.response.ranking.RakingResponse
import com.hsu_irlab.data.response.userinfo.UserInfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("/rank")
    suspend fun getRanking(
        @Query("type")
        type : String
    ): Response<RakingResponse>

    @GET("/user/info")
    suspend fun getUserInfo(
        @Query("user_id")
        user_id: Int
    ): Response<UserInfoResponse>
}