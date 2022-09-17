package com.hsu_irlab.domain.model

data class DomainChallengeDetail (
        val detail: String,
        val example: List<String>,
        val review: List<ReviewDetail>?,
)

data class ReviewDetail(
        val name: String,
        val profile_img: String,
        val review_content: String,
        val review_img: String
)