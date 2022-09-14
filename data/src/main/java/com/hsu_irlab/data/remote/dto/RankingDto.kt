package com.hsu_irlab.data.remote.dto

import com.hsu_irlab.domain.model.DomainRanking

data class RankingDto(
    val Data: List<Ranking>,
    val Message: String
)

data class Ranking(
    val row_num: Int,
    val name: String,
    val total_score: Int,
    val profile_img: String,
    val user_id: Int,
) {
    fun toDomainRanking(): DomainRanking {
        return DomainRanking(
            row_num = row_num,
            name = name,
            total_score = total_score,
            profile_img = profile_img,
            user_id = user_id,
        )
    }
}
