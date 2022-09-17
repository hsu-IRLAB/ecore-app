package com.hsu_irlab.data.remote.dto

import com.hsu_irlab.domain.model.DomainChallengeStart
import com.hsu_irlab.domain.model.DomainChallengeUpload
import com.hsu_irlab.domain.model.DomainChallengeUploadDetail

data class ChallengeUploadDto(
    val Data: List<ChallengeUpload>,
    val Message: String
)
data class ChallengeUpload(
    val good_example: String,
    val bad_example: String,
    val achievement_condition: Int,
    val my_challenge: List<DomainChallengeUploadDetail>,
) {
    fun toDomainChallengeUpload(): DomainChallengeUpload {
        return DomainChallengeUpload(
            good_example = good_example,
            bad_example = bad_example,
            achievement_condition = achievement_condition,
            my_challenge = my_challenge,
        )
    }
}