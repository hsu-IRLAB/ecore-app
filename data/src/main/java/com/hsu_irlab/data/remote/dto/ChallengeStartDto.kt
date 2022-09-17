package com.hsu_irlab.data.remote.dto

import com.hsu_irlab.domain.model.DomainChallengeStart

data class ChallengeStartDto(
    val Data: List<ChallengeStart>,
    val Message: String
){
    fun toDomainChallengeStart(): DomainChallengeStart {
        return DomainChallengeStart(
            user_challenge_id = Data[0].user_challenge_id
        )
    }
}
data class ChallengeStart(
    val user_challenge_id: Int,
)