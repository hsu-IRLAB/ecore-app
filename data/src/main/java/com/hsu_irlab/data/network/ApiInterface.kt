package com.hsu_irlab.data.network

import com.hsu_irlab.data.response.BaseResponse
import com.hsu_irlab.domain.model.DomainBaseRanking
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("/ranking")
    suspend fun getRanking(): Response<DomainBaseRanking>

}