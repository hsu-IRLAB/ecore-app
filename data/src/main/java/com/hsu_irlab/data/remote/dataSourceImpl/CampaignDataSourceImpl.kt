package com.hsu_irlab.data.remote.dataSourceImpl

import com.hsu_irlab.data.remote.dataSource.BadgeDataSource
import com.hsu_irlab.data.remote.dataSource.CampaignDataSource
import com.hsu_irlab.data.remote.dto.BadgeDto
import com.hsu_irlab.data.remote.dto.CampaignDto
import retrofit2.Retrofit
import javax.inject.Inject


class CampaignDataSourceImpl @Inject constructor(
    private val retrofit: Retrofit
): CampaignDataSource {
    override suspend fun getCampaign(): CampaignDto {
        return retrofit.create(CampaignDataSource::class.java).getCampaign()

    }
}