package com.hsu_irlab.data.repository

import com.hsu_irlab.data.remote.dataSource.ChallengeDataSource
import com.hsu_irlab.domain.model.DomainChallenge
import com.hsu_irlab.domain.repository.ChallengeRepository

class ChallengeRepositoryImpl(
    private val api: ChallengeDataSource
) : ChallengeRepository {
    override suspend fun getChallenge(): List<DomainChallenge> {
        return api.getChallenge().Data.map { it.toDomainChallenge() }
    }


}