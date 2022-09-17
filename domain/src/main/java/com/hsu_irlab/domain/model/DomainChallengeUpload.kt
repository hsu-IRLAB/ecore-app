package com.hsu_irlab.domain.model

data class DomainChallengeUpload(
    val good_example: String,
    val bad_example: String,
    val achievement_condition: Int,
    val my_challenge: List<DomainChallengeUploadDetail>,
)
data class DomainChallengeUploadDetail(
    val challenge_img: String,
    val challenge_data: String,
    )