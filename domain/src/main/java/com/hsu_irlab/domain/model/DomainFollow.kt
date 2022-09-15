package com.hsu_irlab.domain.model

data class DomainFollow(
    val profile_img: String,
    val name: String
)

data class DomainFollowSearch(
    val user_id : Int,
    val total_score :Int,
    val profile_img:String,
)