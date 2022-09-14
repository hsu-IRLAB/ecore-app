package com.hsu_irlab.data.repository

import com.hsu_irlab.data.remote.dataSource.RankingDataSource
import com.hsu_irlab.domain.model.DomainRanking
import com.hsu_irlab.domain.repository.RankingRepository

class RankingRepositoryImpl (
    private val api: RankingDataSource
) : RankingRepository {
    override suspend fun getRanking(type: String): List<DomainRanking> {
        return api.getRanking(type).Data.map{ it.toDomainRanking() }
    }
}