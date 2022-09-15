package com.hsu_irlab.data.repository

import com.hsu_irlab.data.remote.dataSource.CampaignDataSource
import com.hsu_irlab.data.remote.dataSource.DailyDataSource
import com.hsu_irlab.domain.model.DomainCampaign
import com.hsu_irlab.domain.model.DomainDaily
import com.hsu_irlab.domain.model.DomainDailyDetail
import com.hsu_irlab.domain.repository.CampaignRepository
import com.hsu_irlab.domain.repository.DailyRepository

class CampaignRepositoryImpl(
    private val api: CampaignDataSource
) : CampaignRepository {
    override suspend fun getCampaign(): List<DomainCampaign> {
        return api.getCampaign().Data.map { it.toDomainCampaign() }
    }
}