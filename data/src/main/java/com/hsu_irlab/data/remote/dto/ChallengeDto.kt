package com.hsu_irlab.data.remote.dto

import com.hsu_irlab.domain.model.ChallengeDetail
import com.hsu_irlab.domain.model.DomainChallenge

data class ChallengeDto(
    val Data: List<Challenge>,
    val Message: String
)

data class Challenge(
    val challenge_id: Int,
    val title: String,
    val term: Int,
    val challenge_reward: Int,
    val participating_person: Int,
    val is_participate: ChallengeDetail?,
){
    fun toDomainChallenge(): DomainChallenge {
        return DomainChallenge(
            challenge_id=challenge_id,
            title=title,
            term=term,
            challenge_reward=challenge_reward,
            participating_person=participating_person,
            is_participate=is_participate,
        )
    }
}