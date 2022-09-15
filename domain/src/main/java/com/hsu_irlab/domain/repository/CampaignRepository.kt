package com.hsu_irlab.domain.repository

import com.hsu_irlab.domain.model.DomainCampaign
import com.hsu_irlab.domain.model.DomainDaily
import com.hsu_irlab.domain.model.DomainDailyDetail


interface CampaignRepository {

    suspend fun getCampaign(): List<DomainCampaign>


}