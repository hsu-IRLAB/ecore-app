package com.hsu_irlab.data.remote.dataSourceImpl

import com.hsu_irlab.data.remote.dataSource.FollowDataSource
import com.hsu_irlab.data.remote.dto.FollowDto
import retrofit2.Retrofit
import javax.inject.Inject

class FollowDataSourceImpl @Inject constructor(
    private val retrofit: Retrofit
): FollowDataSource {
    override suspend fun getFollow(type: String): FollowDto {
        return retrofit.create(FollowDataSource::class.java).getFollow(type)
    }
}