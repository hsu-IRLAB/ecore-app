package com.hsu_irlab.data.repository


import com.hsu_irlab.data.remote.dataSource.DailyDataSource
import com.hsu_irlab.domain.model.DomainDaily
import com.hsu_irlab.domain.model.DomainDailyDetail
import com.hsu_irlab.domain.repository.DailyRepository

class DailyRepositoryImpl(
    private val api: DailyDataSource
) : DailyRepository {
    override suspend fun getDaily(): DomainDaily {
        val data = api.getDaily()
        return data.Data[0].toDomainDaily()
    }

    override suspend fun getDailyDetail(): DomainDailyDetail {
        val data = api.getDailyDetail()
        return data.Data[0].toDomainDailyDetail()
    }
}