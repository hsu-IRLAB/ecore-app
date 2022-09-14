package com.hsu_irlab.ecore.di

import com.hsu_irlab.domain.repository.BadgeRepository
import com.hsu_irlab.domain.repository.DailyRepository
import com.hsu_irlab.domain.repository.RankingRepository
import com.hsu_irlab.domain.repository.UserRepository
import com.hsu_irlab.domain.use_case.BadgeUseCase
import com.hsu_irlab.domain.use_case.DailyUseCase
import com.hsu_irlab.domain.use_case.RankingUseCase
import com.hsu_irlab.domain.use_case.UserUseCase
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

}