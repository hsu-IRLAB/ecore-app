package com.hsu_irlab.data

import com.google.gson.JsonObject
import com.hsu_irlab.data.network.NetworkModule
import com.hsu_irlab.data.response.changename.toDomainChangeName
import com.hsu_irlab.data.response.nameexist.toDomainUserExist
import com.hsu_irlab.domain.model.DomainChangeName
import com.hsu_irlab.domain.model.DomainUserExist
import com.hsu_irlab.domain.repository.UserExistRepository

class UserExistRepositoryImpl() : UserExistRepository {
    override suspend fun getUserExist(name : String): DomainUserExist {
        return NetworkModule.getRetrofitService.getUserExist(name)
            .body()?.toDomainUserExist()?: DomainUserExist("","") }

    override suspend fun putChangeName(name: String): DomainChangeName {
        val jsonData: JsonObject = JsonObject().apply {
            addProperty("name",name)
        }
        return NetworkModule.getRetrofitService.putChangeName(jsonData)
            .body()?.toDomainChangeName()?: DomainChangeName("","")
    }


    companion object {
        private var instance: UserExistRepositoryImpl? = null
        fun getInstance() : UserExistRepositoryImpl? {
            if(instance == null) instance = UserExistRepositoryImpl()
            return instance
        }
    }
}