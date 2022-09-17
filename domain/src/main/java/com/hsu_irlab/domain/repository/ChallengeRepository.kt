package com.hsu_irlab.domain.repository

import com.hsu_irlab.domain.model.DomainCampaign
import com.hsu_irlab.domain.model.DomainChallenge
import com.hsu_irlab.domain.model.DomainChallengeDetail
import com.hsu_irlab.domain.model.DomainReview

interface ChallengeRepository {
    suspend fun getChallenge(): List<DomainChallenge>

    suspend fun postReview(uCId:Int,content:String): DomainReview

    suspend fun getChallengeDetail(id:Int): List<DomainChallengeDetail>
}