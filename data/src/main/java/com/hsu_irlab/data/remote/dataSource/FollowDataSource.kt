package com.hsu_irlab.data.remote.dataSource

import com.hsu_irlab.data.remote.dto.FollowDto
import retrofit2.http.GET
import retrofit2.http.Query

interface FollowDataSource {
    @GET("/user/follow")
    suspend fun getFollow(
        @Query("type")
        type:String
    ): FollowDto
}