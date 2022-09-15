package com.hsu_irlab.data.remote.dataSource

import com.hsu_irlab.data.remote.dto.FollowDto
import com.hsu_irlab.data.remote.dto.FollowSearchDto
import retrofit2.Call
import retrofit2.http.*

interface FollowDataSource {
    @GET("/user/follow")
    suspend fun getFollow(
        @Query("type")
        type:String
    ): FollowDto

    @GET("/user/follow/search")
    suspend fun getFollowSearch(
        @Query("name")
        name: String
    ): FollowSearchDto

    @FormUrlEncoded
    @POST("/user/follow")
    suspend fun addFollow(
        @Field("target_id") target_id:Int
    )

}