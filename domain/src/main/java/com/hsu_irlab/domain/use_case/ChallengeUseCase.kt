package com.hsu_irlab.domain.use_case

import com.hsu_irlab.domain.model.DomainChallenge
import com.hsu_irlab.domain.model.DomainChallengeDetail
import com.hsu_irlab.domain.model.DomainReview
import com.hsu_irlab.domain.repository.ChallengeRepository

class ChallengeUseCase(
    private val repository: ChallengeRepository
){
    suspend fun getChallenge():List<DomainChallenge>{
        return repository.getChallenge()
    }

    suspend fun postReview(uCId:Int, content: String): DomainReview{
        return repository.postReview(uCId,content)
    }

    suspend fun getChallengeDetail(id:Int):List<DomainChallengeDetail>{
        return repository.getChallengeDetail(id)
    }

}