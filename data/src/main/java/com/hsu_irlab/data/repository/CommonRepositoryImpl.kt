package com.hsu_irlab.data.repository

import com.google.gson.JsonObject
import com.hsu_irlab.data.remote.dataSource.CommonDataSource
import com.hsu_irlab.domain.model.DomainImagePostResult
import com.hsu_irlab.domain.model.DomainImages
import com.hsu_irlab.domain.repository.CommonRepository
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class CommonRepositoryImpl(
    private val api: CommonDataSource
) : CommonRepository {
    override suspend fun getProfileImg(
        type: String,
        count: String,
        target: Int
    ): List<DomainImages> {
        return api.getImage(type, count, target).Data.map { it.toDomainImages() }
    }

    override suspend fun postImage(type: String, target: Int, image: File): DomainImagePostResult {
        val jsonData: JsonObject = JsonObject().apply{
            addProperty("type",type)
            addProperty("target",target)
        }
        val requestFile = image.asRequestBody("image/png".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("userfile", image?.name,requestFile)
        val data = api.postImg(jsonData,body)
        return data.toDomainImagePostResult()
    }
}