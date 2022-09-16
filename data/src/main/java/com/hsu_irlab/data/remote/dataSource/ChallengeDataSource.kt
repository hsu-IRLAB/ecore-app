package com.hsu_irlab.data.remote.dataSource

import com.google.gson.JsonObject
import com.hsu_irlab.data.remote.dto.ChallengeDto
import com.hsu_irlab.data.remote.dto.ReviewDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface ChallengeDataSource {
    @GET("/challenge")
    suspend fun getChallenge(): ChallengeDto

    @POST("/challenge/review")
    suspend fun postReview(@Body query: JsonObject): ReviewDto
}