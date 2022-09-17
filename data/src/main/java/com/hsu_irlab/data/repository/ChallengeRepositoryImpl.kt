package com.hsu_irlab.data.repository

import com.google.gson.JsonObject
import com.hsu_irlab.data.remote.dataSource.ChallengeDataSource
import com.hsu_irlab.data.remote.dto.toDomainUserChangeName
import com.hsu_irlab.domain.model.*
import com.hsu_irlab.domain.repository.ChallengeRepository

class ChallengeRepositoryImpl(
    private val api: ChallengeDataSource
) : ChallengeRepository {
    override suspend fun getChallenge(): List<DomainChallenge> {
        return api.getChallenge().Data.map { it.toDomainChallenge() }
    }

    override suspend fun postReview(uCId:Int,content:String): DomainReview {
        val jsonData: JsonObject= JsonObject().apply{
            addProperty("user_challenge_id",uCId)
            addProperty("review_content",content)
        }
        val data = api.postReview(jsonData)
        return data.toDomainReview()
    }

    override suspend fun postChallenge(CId: Int): DomainChallengeStart {
        val jsonData: JsonObject= JsonObject().apply{
            addProperty("challenge_id",CId)
        }
        return api.postChallenge(jsonData).toDomainChallengeStart()
    }

    override suspend fun getChallengeUploadDetail(id: Int): List<DomainChallengeUpload> {

        return api.getChallengeUploadDetail(id).Data.map { it.toDomainChallengeUpload() }
    }

    override suspend fun getChallengeDetail(id:Int): List<DomainChallengeDetail> {
        return api.getChallengeDetail(id).Data.map { it.toDomainChallengeDetail() }
    }
}