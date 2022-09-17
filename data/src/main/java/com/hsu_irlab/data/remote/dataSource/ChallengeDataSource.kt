package com.hsu_irlab.data.remote.dataSource

import com.google.gson.JsonObject
import com.hsu_irlab.data.remote.dto.ChallengeDetailDto
import com.hsu_irlab.data.remote.dto.ChallengeDto
import com.hsu_irlab.data.remote.dto.ReviewDto
import retrofit2.http.*

interface ChallengeDataSource {
    @GET("/challenge")
    suspend fun getChallenge(): ChallengeDto

    @POST("/challenge/review")
    suspend fun postReview(@Body query: JsonObject): ReviewDto

    @GET("/challenge/detail")
    suspend fun getChallengeDetail(
        @Query("challenge_id")
        type: Int
    ): ChallengeDetailDto
}