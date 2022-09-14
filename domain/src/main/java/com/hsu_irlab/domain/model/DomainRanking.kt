package com.hsu_irlab.domain.model

data class DomainRanking (
    val row_num:Int,
    val name: String,
    val total_score: Int,
    val profile_img: String,
    val user_id: Int,
)
