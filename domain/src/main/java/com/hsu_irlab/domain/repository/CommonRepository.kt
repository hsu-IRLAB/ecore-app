package com.hsu_irlab.domain.repository

import com.hsu_irlab.domain.model.DomainImages

interface CommonRepository {
    suspend fun getProfileImg(type:String,count:String,target:Int):List<DomainImages>
}