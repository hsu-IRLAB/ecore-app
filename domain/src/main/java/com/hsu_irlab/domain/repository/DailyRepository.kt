package com.hsu_irlab.domain.repository

import com.hsu_irlab.domain.model.DomainBadge
import com.hsu_irlab.domain.model.DomainDaily
import com.hsu_irlab.domain.model.DomainDailyDetail

interface DailyRepository {

    suspend fun getDaily():DomainDaily

    suspend fun getDailyDetail():DomainDailyDetail

}