package com.hsu_irlab.data.remote.dto

import com.hsu_irlab.domain.model.DomainChallengeDetail
import com.hsu_irlab.domain.model.ReviewDetail

data class ChallengeDetailDto(
    val Data: List<ChallengeDetail>,
    val Message: String
)

data class ChallengeDetail(
    val detail: String,
    val example: List<String>,
    val review: List<ReviewDetail>?
){
    fun toDomainChallengeDetail():DomainChallengeDetail{
        return DomainChallengeDetail(
            detail =detail,
            example=example,
            review = review
        )
    }
}
