package com.hsu_irlab.data.remote.dataSourceImpl

import com.hsu_irlab.data.remote.dataSource.CampaignDataSource
import com.hsu_irlab.data.remote.dataSource.ChallengeDataSource
import com.hsu_irlab.data.remote.dto.CampaignDto
import com.hsu_irlab.data.remote.dto.ChallengeDto
import retrofit2.Retrofit
import javax.inject.Inject

class ChallengeDataSourceImpl @Inject constructor(
    private val retrofit: Retrofit
): ChallengeDataSource {
    override suspend fun getChallenge(): ChallengeDto {
        return retrofit.create(ChallengeDataSource::class.java).getChallenge()

    }
}