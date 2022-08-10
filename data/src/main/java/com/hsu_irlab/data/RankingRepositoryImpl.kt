package com.hsu_irlab.data

import android.app.Application
import com.hsu_irlab.data.network.NetworkModule
import com.hsu_irlab.data.response.BaseResponse
import com.hsu_irlab.domain.model.DomainBaseRanking
import com.hsu_irlab.domain.repository.RankingRepository

class RankingRepositoryImpl(application: Application):RankingRepository {
    override suspend fun getRanking(): DomainBaseRanking {
        val response = NetworkModule.getRetrofitService.getRanking()
//        val response = response2.body()
//        val response =  NetworkModule.getRetrofitService.getRanking()

        //이부분이 문제임
//        return  if (response.isSuccessful) response.body() as DomainBaseRanking else DomainBaseRanking(ArrayList())
        return  if (response.isSuccessful) response.body() as DomainBaseRanking else DomainBaseRanking(ArrayList())
    }

    companion object {
        private var instance: RankingRepositoryImpl? = null
        fun getInstance(application : Application): RankingRepositoryImpl? {
            if (instance == null) instance = RankingRepositoryImpl(application)
            return instance
        }
    }
}