package com.hsu_irlab.data.response.ranking

import com.hsu_irlab.domain.model.DomainRanking

data class RankingDataResponse (
    val name: String,
    val profile_picture: String,
    val total_score: String,
    val user_id: Int
)

fun RankingDataResponse.toDomainRanking(): DomainRanking = DomainRanking(
     name,profile_picture,total_score,user_id)

