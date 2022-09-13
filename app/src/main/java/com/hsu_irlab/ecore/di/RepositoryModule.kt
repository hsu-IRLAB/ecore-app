package com.hsu_irlab.ecore.di

import com.hsu_irlab.data.remote.dataSource.BadgeDataSource
import com.hsu_irlab.data.remote.dataSource.DailyDataSource
import com.hsu_irlab.data.remote.dataSource.UserDataSource
import com.hsu_irlab.data.repository.BadgeRepositoryImpl
import com.hsu_irlab.data.repository.DailyRepositoryImpl
import com.hsu_irlab.data.repository.UserRepositoryImpl
import com.hsu_irlab.domain.repository.BadgeRepository
import com.hsu_irlab.domain.repository.DailyRepository
import com.hsu_irlab.domain.repository.UserRepository
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
}