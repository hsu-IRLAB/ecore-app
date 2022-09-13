package com.hsu_irlab.domain.use_case

import com.hsu_irlab.domain.model.DomainUserInfo
import com.hsu_irlab.domain.repository.UserRepository

class UserUseCase(
    private val repository: UserRepository
){
    suspend fun getUserInfo(user_id:Int):DomainUserInfo{
        return repository.getUserInfo(user_id)
    }

}