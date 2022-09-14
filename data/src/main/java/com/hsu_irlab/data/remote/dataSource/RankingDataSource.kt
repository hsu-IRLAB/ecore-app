package com.hsu_irlab.data.remote.dataSource

import com.hsu_irlab.data.response.ranking.RakingResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RankingDataSource {
    @GET("/rank")
    suspend fun getRanking(
        @Query("type")
        type : String
    ): RakingResponse
}