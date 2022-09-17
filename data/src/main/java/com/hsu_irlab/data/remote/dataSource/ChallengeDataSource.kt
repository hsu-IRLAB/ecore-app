package com.hsu_irlab.data.remote.dataSource

import com.google.gson.JsonObject
import com.hsu_irlab.data.remote.dto.*
import retrofit2.http.*

interface ChallengeDataSource {
    @GET("/challenge")
    suspend fun getChallenge(): ChallengeDto

    @POST("/challenge/start")
    suspend fun postChallenge(@Body query: JsonObject):ChallengeStartDto

    @GET("/challenge/upload/detail")
    suspend fun getChallengeUploadDetail(
        @Query("user_challenge_id")
        id: Int
    ):ChallengeUploadDto

    @POST("/challenge/review")
    suspend fun postReview(@Body query: JsonObject): ReviewDto

    @GET("/challenge/detail")
    suspend fun getChallengeDetail(
        @Query("challenge_id")
        type: Int
    ): ChallengeDetailDto
}