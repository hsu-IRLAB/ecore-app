package com.hsu_irlab.data.remote.dataSource

import com.hsu_irlab.data.remote.dto.DailyDetailDto
import com.hsu_irlab.data.remote.dto.DailyDto
import retrofit2.Response
import retrofit2.http.GET

interface DailyDataSource {
    @GET("/dailychallenge")
    suspend fun getDaily(): DailyDto

    @GET("/dailychallenge/details")
    suspend fun getDailyDetail(): DailyDetailDto
}