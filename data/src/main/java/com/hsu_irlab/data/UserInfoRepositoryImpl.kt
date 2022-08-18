package com.hsu_irlab.data

import android.util.Log
import com.hsu_irlab.data.network.NetworkModule
import com.hsu_irlab.data.response.userinfo.toDomainUserInfo
import com.hsu_irlab.domain.model.DomainUserInfo
import com.hsu_irlab.domain.repository.UserInfoRepository

class UserInfoRepositoryImpl(): UserInfoRepository {
    override suspend fun getUserInfo(user_id : Int): DomainUserInfo {
        val resData = NetworkModule.getRetrofitService.getUserInfo(user_id)
            .body()?.Data

//        Log.e("d", "getUserInfo:ssssssssssss $resData", )


        return NetworkModule.getRetrofitService.getUserInfo(user_id)
            .body()?.Data?.toDomainUserInfo()?:DomainUserInfo(0,0,0,0,"","",0) }
    companion object {
        private var instance: UserInfoRepositoryImpl? = null
        fun getInstance() : UserInfoRepositoryImpl? {
            if(instance == null) instance = UserInfoRepositoryImpl()
            return instance
        }
    }
}

