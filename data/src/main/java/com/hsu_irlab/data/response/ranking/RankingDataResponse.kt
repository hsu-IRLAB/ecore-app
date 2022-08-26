package com.hsu_irlab.data.response.ranking

import com.hsu_irlab.domain.model.DomainRanking

data class RankingDataResponse (
    val row_num:String,
    val user_id: Int,
    val name: String,
    val total_score: String,
    val profile_picture: String
)

fun RankingDataResponse.toDomainRanking(): DomainRanking = DomainRanking(
    row_num,user_id,name,total_score,profile_picture?: "dog.jpg")

