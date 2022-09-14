package com.hsu_irlab.domain.model

data class DomainDailyDetail(
    val daily_challenge_id: Int,
    val detail: String,
    val good_ex: String,
    val bad_ex: String,
)