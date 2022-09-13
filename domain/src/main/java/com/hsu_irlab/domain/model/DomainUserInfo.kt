package com.hsu_irlab.domain.model

data class DomainUserInfo(
    val challenge_done: Int,
    val daily_challenge_done: Int,
    val following: Int,
    val follower: Int,
    val name: String,
    val profile_img: String,
    val total_score: Int
)
