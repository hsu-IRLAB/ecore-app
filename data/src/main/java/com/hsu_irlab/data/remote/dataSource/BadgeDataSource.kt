package com.hsu_irlab.data.remote.dataSource

import com.hsu_irlab.data.remote.dto.BadgeDto
import retrofit2.Response
import retrofit2.http.GET

interface BadgeDataSource {
    @GET("/user/badge")
    suspend fun getBadge():BadgeDto
}