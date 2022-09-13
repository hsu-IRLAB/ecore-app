package com.hsu_irlab.ecore.di

import com.hsu_irlab.domain.repository.BadgeRepository
import com.hsu_irlab.domain.use_case.BadgeUseCase
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


}