package com.hsu_irlab.domain.model

data class DomainDaily(
    val title: String,
    val daily_reward: Int,
    val info: DomainDailyInfo?
)
data class DomainDailyInfo(
    val daily_img: String,
    val daily_good: Int,
    val daily_date: String,
)