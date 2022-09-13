package com.hsu_irlab.data

import android.util.Log
import com.hsu_irlab.data.remote.BadgeDataSource
import com.hsu_irlab.data.remote.dto.BadgeDto
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject


class BadgeDataSourceImpl @Inject constructor(
    private val retrofit: Retrofit
): BadgeDataSource {
    override suspend fun getBadge(): Response<BadgeDto> {
        return retrofit.create(BadgeDataSource::class.java).getBadge()
    }
}