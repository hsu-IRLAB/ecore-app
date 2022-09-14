package com.hsu_irlab.data.remote.dataSourceImpl

import com.hsu_irlab.data.remote.dataSource.RankingDataSource
import com.hsu_irlab.data.remote.dto.RankingDto
import retrofit2.Retrofit
import javax.inject.Inject

class RankingDataSourceImpl @Inject constructor(
    private val retrofit: Retrofit
): RankingDataSource {
    override suspend fun getRanking(type: String): RankingDto {
        return retrofit.create(RankingDataSource::class.java).getRanking(type)
    }
}
