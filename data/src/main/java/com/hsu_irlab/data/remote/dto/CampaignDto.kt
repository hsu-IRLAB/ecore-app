package com.hsu_irlab.data.remote.dto

import com.hsu_irlab.domain.model.DomainCampaign
import com.hsu_irlab.domain.model.DomainDailyDetail

data class CampaignDto(
    val Data: List<Campaign>,
    val Message: String
)
data class Campaign(
    val campaign_id: Int,
    val title: String,
    val start_date: String,
    val end_date: String,
    val detail: String,
    val poster_img: String,
    val campaign_reward: Int,
){
    fun toDomainCampaign(): DomainCampaign {
        return DomainCampaign(
            campaign_id=campaign_id,
            title=title,
            start_date=start_date,
            end_date=end_date,
            detail=detail,
            poster_img=poster_img,
            campaign_reward=campaign_reward,
        )
    }
}