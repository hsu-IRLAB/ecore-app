package com.hsu_irlab.data.response.userinfo

import com.hsu_irlab.domain.model.DomainUserInfo

data class UserInfoDataResponse(
    val challenge_done: Int,
    val daily_challenge_done: Int,
    val following: Int,
    val follwer: Int,
    val name: String,
    val profile_img: String,
    val total_score: Int
)

fun UserInfoDataResponse.toDomainUserInfo(): DomainUserInfo = DomainUserInfo(
    challenge_done,daily_challenge_done,following,follwer,name,profile_img?:"",total_score
)
