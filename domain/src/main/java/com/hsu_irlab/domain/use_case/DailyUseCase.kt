package com.hsu_irlab.domain.use_case

import com.hsu_irlab.domain.model.DomainBadge
import com.hsu_irlab.domain.model.DomainDaily
import com.hsu_irlab.domain.model.DomainDailyDetail
import com.hsu_irlab.domain.repository.BadgeRepository
import com.hsu_irlab.domain.repository.DailyRepository

class DailyUseCase(
    private val repository: DailyRepository
){
    suspend fun getDaily():DomainDaily{
        return repository.getDaily()
    }
    suspend fun getDailyDetail():DomainDailyDetail{
        return repository.getDailyDetail()
    }


}