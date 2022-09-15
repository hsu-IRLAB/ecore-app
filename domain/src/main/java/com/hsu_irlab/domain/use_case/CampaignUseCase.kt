package com.hsu_irlab.domain.use_case

import com.hsu_irlab.domain.model.DomainCampaign
import com.hsu_irlab.domain.repository.CampaignRepository

class CampaignUseCase(
    private val repository: CampaignRepository
){
    suspend fun getCampaign():List<DomainCampaign>{
        return repository.getCampaign()
    }

}