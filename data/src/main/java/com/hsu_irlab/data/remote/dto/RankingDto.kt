package com.hsu_irlab.data.remote.dto

import com.hsu_irlab.domain.model.DomainRanking

data class RankingDto(
    val Data: List<Ranking>,
    val Message: String
)

data class Ranking(
    val row_num:String,
    val user_id: Int,
    val name: String,
    val total_score: String,
    val profile_picture: String
){
    fun toDomainRanking(): DomainRanking {
        return DomainRanking(
            row_num=row_num,
            user_id=user_id,
            name=name,
            total_score=total_score,
            profile_picture=profile_picture,
        )
    }
}
