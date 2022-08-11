package com.hsu_irlab.domain.repository

import android.app.Application
import com.hsu_irlab.domain.model.DomainRanking
interface RankingRepository{
    suspend fun getRanking(): List<DomainRanking>

}