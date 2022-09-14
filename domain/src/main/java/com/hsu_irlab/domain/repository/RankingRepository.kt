package com.hsu_irlab.domain.repository

import com.hsu_irlab.domain.model.DomainRanking
interface RankingRepository{
    suspend fun getRanking(type:String): List<DomainRanking>

}