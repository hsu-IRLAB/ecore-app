package com.hsu_irlab.domain.repository

import com.hsu_irlab.domain.model.DomainChangeName
import com.hsu_irlab.domain.model.DomainUserExist

interface UserExistRepository {
    suspend fun getUserExist(name : String): DomainUserExist

    suspend fun putChangeName(name : String) : DomainChangeName
}