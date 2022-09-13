package com.hsu_irlab.data.remote.dto

import com.hsu_irlab.domain.model.DomainUserInfo

data class UserInfoDto(
    val Data: UserInfo,
    val Message: String
)

data class UserInfo(
    val name: String,
    val total_score: Int,
    val profile_img: String,
    val daily_challenge_done: Int,
    val challenge_done: Int,
    val following: Int,
    val follower: Int,
){
    fun toDomainUserInfo(): DomainUserInfo {
        return DomainUserInfo(
            name=name,
            total_score=total_score,
            profile_img=profile_img,
            daily_challenge_done=daily_challenge_done,
            challenge_done=challenge_done,
            following=following,
            follower=follower,
        )
    }
}