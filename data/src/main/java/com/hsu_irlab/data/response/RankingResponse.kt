package com.hsu_irlab.data.response

data class RankingResponse (
    val name: String,
    val profile_picture: String,
    val total_score: String,
    val user_id: Int
)

//fun RankingResponse.toDomainRanking(): DomainRanking = DomainRanking(
//     name,profile_picture,total_score,user_id)

