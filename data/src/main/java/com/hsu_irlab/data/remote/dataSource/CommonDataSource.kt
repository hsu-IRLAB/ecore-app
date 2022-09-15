package com.hsu_irlab.data.remote.dataSource

import com.hsu_irlab.data.remote.dto.ImagesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CommonDataSource {
    @GET("/image")
    suspend fun getImage(
        @Query("type")
        type: String,
        @Query("count")
        count: String,
        @Query("target")
        target: Int
    ):ImagesDto
}