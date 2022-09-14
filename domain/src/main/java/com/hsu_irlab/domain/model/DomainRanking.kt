package com.hsu_irlab.domain.model

data class DomainRanking (
    val row_num:String,
    val user_id: Int,
    val name: String,
    val total_score: String,
    val profile_picture: String
)
