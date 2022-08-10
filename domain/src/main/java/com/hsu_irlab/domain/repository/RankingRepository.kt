package com.hsu_irlab.domain.repository

import android.app.Application
import com.hsu_irlab.domain.model.DomainBaseRanking

interface RankingRepository{
    suspend fun getRanking(): DomainBaseRanking

}