package com.hsu_irlab.domain.repository

import com.hsu_irlab.domain.model.DomainCampaign
import com.hsu_irlab.domain.model.DomainChallenge

interface ChallengeRepository {
    suspend fun getChallenge(): List<DomainChallenge>
}