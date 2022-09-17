package com.hsu_irlab.domain.use_case

import com.hsu_irlab.domain.model.*
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

    suspend fun postChallenge(CId:Int): DomainChallengeStart{
        return repository.postChallenge(CId)
    }

    suspend fun getChallengeUploadDetail(id:Int): List<DomainChallengeUpload>{
        return repository.getChallengeUploadDetail(id)
    }

}