package com.hsu_irlab.domain.repository

import com.hsu_irlab.domain.model.*

interface ChallengeRepository {
    suspend fun getChallenge(): List<DomainChallenge>

    suspend fun postReview(uCId:Int,content:String): DomainReview

    suspend fun postChallenge(CId:Int): DomainChallengeStart

    suspend fun getChallengeUploadDetail(id:Int): List<DomainChallengeUpload>


    suspend fun getChallengeDetail(id:Int): List<DomainChallengeDetail>
}