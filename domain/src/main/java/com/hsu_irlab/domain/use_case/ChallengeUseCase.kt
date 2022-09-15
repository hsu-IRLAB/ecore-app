package com.hsu_irlab.domain.use_case

import com.hsu_irlab.domain.model.DomainChallenge
import com.hsu_irlab.domain.repository.ChallengeRepository

class ChallengeUseCase(
    private val repository: ChallengeRepository
){
    suspend fun getChallenge():List<DomainChallenge>{
        return repository.getChallenge()
    }

}