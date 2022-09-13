package com.hsu_irlab.ecore.di

import com.hsu_irlab.data.remote.BadgeDataSource
import com.hsu_irlab.data.repository.BadgeRepositoryImpl
import com.hsu_irlab.domain.repository.BadgeRepository
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
        api:BadgeDataSource
    ):BadgeRepository{
        return BadgeRepositoryImpl(api)
    }
}