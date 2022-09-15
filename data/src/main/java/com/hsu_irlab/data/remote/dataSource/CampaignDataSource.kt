package com.hsu_irlab.data.remote.dataSource

import com.hsu_irlab.data.remote.dto.BadgeDto
import com.hsu_irlab.data.remote.dto.CampaignDto
import retrofit2.http.GET

interface CampaignDataSource {
    @GET("/campaign")
    suspend fun getCampaign(): CampaignDto
}