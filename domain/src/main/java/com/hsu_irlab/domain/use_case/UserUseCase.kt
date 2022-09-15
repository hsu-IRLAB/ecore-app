package com.hsu_irlab.domain.use_case

import com.hsu_irlab.domain.model.DomainUserChangeName
import com.hsu_irlab.domain.model.DomainUserExist
import com.hsu_irlab.domain.model.DomainUserInfo
import com.hsu_irlab.domain.repository.UserRepository

class UserUseCase(
    private val repository: UserRepository
){
    suspend fun getUserInfo(user_id:Int):DomainUserInfo{
        return repository.getUserInfo(user_id)
    }

    suspend fun getUserExist(name : String) : DomainUserExist{
        return repository.getUserExist(name)
    }

    suspend fun putUserName(name: String) :DomainUserChangeName{
        return repository.putUserName(name)
    }
}