package com.hsu_irlab.domain.use_case

import com.hsu_irlab.domain.model.DomainRanking
import com.hsu_irlab.domain.repository.RankingRepository

class RankingUseCase(
    private val repository: RankingRepository
) {
    suspend fun getWholeRanking(): List<DomainRanking>{
        return repository.getRanking("all")
    }
    suspend fun getFollowingRanking(): List<DomainRanking>{
        return repository.getRanking("following")
    }
    suspend fun getMyRanking(type:String): List<DomainRanking>{
        return repository.getRanking(type)
    }
}