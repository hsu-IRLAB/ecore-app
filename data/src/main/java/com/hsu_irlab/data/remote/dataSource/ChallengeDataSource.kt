package com.hsu_irlab.data.remote.dataSource

import com.hsu_irlab.data.remote.dto.ChallengeDto
import retrofit2.http.GET

interface ChallengeDataSource {
    @GET("/challenge")
    suspend fun getChallenge(): ChallengeDto
}