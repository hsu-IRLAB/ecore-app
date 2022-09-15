package com.hsu_irlab.ecore.di

import com.hsu_irlab.data.remote.dataSource.*
import com.hsu_irlab.data.remote.dataSourceImpl.ChallengeDataSourceImpl
import com.hsu_irlab.data.repository.*
import com.hsu_irlab.domain.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideBadgeRepository(
        api: BadgeDataSource
    ):BadgeRepository{
        return BadgeRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        api: UserDataSource
    ):UserRepository{
        return UserRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideDailyRepository(
        api: DailyDataSource
    ):DailyRepository{
        return DailyRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideRankingRepository(
        api: RankingDataSource
    ):RankingRepository{
        return RankingRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideFollowRepository(
        api: FollowDataSource
    ):FollowRepository{
        return FollowRepositoryImpl(api)
    }
    @Provides
    @Singleton
    fun provideCampaignRepository(
        api: CampaignDataSource
    ):CampaignRepository{
        return CampaignRepositoryImpl(api)
    }
    @Provides
    @Singleton
    fun provideChallengeRepository(
        api: ChallengeDataSourceImpl
    ):ChallengeRepository{
        return ChallengeRepositoryImpl(api)
    }

}