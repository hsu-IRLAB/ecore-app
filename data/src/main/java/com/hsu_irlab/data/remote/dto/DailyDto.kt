package com.hsu_irlab.data.remote.dto

import com.hsu_irlab.domain.model.DomainDaily
import com.hsu_irlab.domain.model.DomainDailyInfo

data class DailyDto(
    val Data: List<Daily>,
    val Message: String
)
data class Daily(
    val title: String,
    val daily_reward: Int,
    val info: DomainDailyInfo?
){
    fun toDomainDaily(): DomainDaily {
        return DomainDaily(
            title=title,
            daily_reward =daily_reward,
            info = info,
        )
    }
}

