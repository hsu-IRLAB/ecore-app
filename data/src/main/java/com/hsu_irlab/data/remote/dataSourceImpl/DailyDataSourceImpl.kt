package com.hsu_irlab.data.remote.dataSourceImpl

import com.hsu_irlab.data.remote.dataSource.DailyDataSource
import com.hsu_irlab.data.remote.dto.DailyDetailDto
import com.hsu_irlab.data.remote.dto.DailyDto
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class DailyDataSourceImpl @Inject constructor(
    private val retrofit: Retrofit
): DailyDataSource {
    override suspend fun getDaily(): DailyDto {
        return retrofit.create(DailyDataSource::class.java).getDaily()
    }

    override suspend fun getDailyDetail(): DailyDetailDto {
        return retrofit.create(DailyDataSource::class.java).getDailyDetail()
    }
}
