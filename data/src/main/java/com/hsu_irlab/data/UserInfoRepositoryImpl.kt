package com.hsu_irlab.data

import com.hsu_irlab.data.network.NetworkModule1
import com.hsu_irlab.data.response.userinfo.toDomainUserInfo
import com.hsu_irlab.domain.model.DomainUserInfo
import com.hsu_irlab.domain.repository.UserRepository

class UserInfoRepositoryImpl(): UserRepository {
    override suspend fun getUserInfo(user_id : Int): DomainUserInfo {
        val resData = NetworkModule1.getRetrofitService.getUserInfo(user_id)
            .body()?.Data

//        Log.e("d", "getUserInfo:ssssssssssss $resData", )


        return NetworkModule1.getRetrofitService.getUserInfo(user_id)
            .body()?.Data?.toDomainUserInfo()?:DomainUserInfo(0,0,0,0,"","",0) }
    companion object {
        private var instance: UserInfoRepositoryImpl? = null
        fun getInstance() : UserInfoRepositoryImpl? {
            if(instance == null) instance = UserInfoRepositoryImpl()
            return instance
        }
    }
}

