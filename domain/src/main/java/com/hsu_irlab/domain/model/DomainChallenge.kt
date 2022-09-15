package com.hsu_irlab.domain.model

data class DomainChallenge (
    val challenge_id: Int,
    val title: String,
    val term: Int,
    val challenge_reward: Int,
    val participating_person: Int,
    val is_participate: ChallengeDetail?,
)
data class ChallengeDetail(
    val user_challenge_id: Int,
    val start_date: String,
)