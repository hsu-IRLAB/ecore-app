package com.hsu_irlab.data.remote.dto

import com.hsu_irlab.domain.model.DomainFollowSearch

data class FollowSearchDto(
    val Message : String,
    val Data: List<FollowSearch>?,
    val Error_message: String?
)
data class FollowSearch(
    val user_id:Int,
    val total_score:Int,
    val profile_img:String
){
    fun toDomainFollowSearch(): DomainFollowSearch
    {
        return DomainFollowSearch(
            user_id=user_id,
            total_score=total_score,
            profile_img = profile_img,
        )
    }
}
