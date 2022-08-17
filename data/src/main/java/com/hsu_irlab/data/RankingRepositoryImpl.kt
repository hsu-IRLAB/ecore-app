package com.hsu_irlab.data

import android.app.Application
import com.hsu_irlab.data.network.NetworkModule
import com.hsu_irlab.data.response.ranking.toDomainRanking
import com.hsu_irlab.domain.model.DomainRanking
import com.hsu_irlab.domain.repository.RankingRepository


class RankingRepositoryImpl() :RankingRepository {
    override suspend fun getRanking(): List<DomainRanking> {
        return NetworkModule.getRetrofitService.getRanking()
            .body()?.data?.map { it.toDomainRanking() } ?: ArrayList() // 값 돌아와서 null 아니면 domain ranking 으로 바꿔서 리턴 null
    }
    companion object {
        private var instance: RankingRepositoryImpl? = null
        fun getInstance(): RankingRepositoryImpl? {
            if (instance == null) instance = RankingRepositoryImpl()
            return instance
        }
    }

}