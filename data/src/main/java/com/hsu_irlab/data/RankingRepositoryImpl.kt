package com.hsu_irlab.data

import android.app.Application
import android.util.Log
import com.hsu_irlab.data.network.NetworkModule
import com.hsu_irlab.data.response.BaseResponse
//import com.hsu_irlab.domain.model.DomainBaseRanking

class RankingRepositoryImpl(application: Application):RankingRepository {
    override suspend fun getRanking(): BaseResponse {
        val response = NetworkModule.getRetrofitService.getRanking()
//        val response = response2.body()
//        val response =  NetworkModule.getRetrofitService.getRanking()

        //이부분이 문제임
//        return  if (response.isSuccessful) response.body() as DomainBaseRanking else DomainBaseRanking(ArrayList())
        Log.e("responseresponseresponseresponse", "response: ${response}", )
        Log.e("message", "getRanking: ${response.message()}", )
        Log.e("body", "getRanking: ${response.body()}", )
        Log.e("isSuccessful", "getRanking: ${response.isSuccessful}", )
        return  if (response.isSuccessful) response.body() as BaseResponse else BaseResponse(ArrayList())
    }

    companion object {
        private var instance: RankingRepositoryImpl? = null
        fun getInstance(application : Application): RankingRepositoryImpl? {
            if (instance == null) instance = RankingRepositoryImpl(application)
            return instance
        }
    }
}