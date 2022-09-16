package com.hsu_irlab.ecore.di

import com.hsu_irlab.domain.repository.*
import com.hsu_irlab.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideBadgeUseCase(repository: BadgeRepository):BadgeUseCase{
        return BadgeUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideUserUseCase(repository: UserRepository):UserUseCase{
        return UserUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideDailyUseCase(repository: DailyRepository):DailyUseCase{
        return DailyUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideRankingUseCase(repository: RankingRepository):RankingUseCase{
        return RankingUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideFollowUseCase(repository: FollowRepository):FollowUseCase{
        return FollowUseCase(repository)
    }
    @Provides
    @Singleton
    fun provideCampaignUseCase(repository: CampaignRepository):CampaignUseCase{
        return CampaignUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideCommonUseCase(repository: CommonRepository):CommonUseCase{
        return CommonUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideChallengeUseCase(repository: ChallengeRepository):ChallengeUseCase{
        return ChallengeUseCase(repository)
    }
}