package com.hsu_irlab.ecore.di

import com.hsu_irlab.data.remote.dataSource.*
import com.hsu_irlab.data.remote.dataSourceImpl.*
import com.hsu_irlab.data.repository.FollowRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideBadgeDataSource(
        retrofit: Retrofit
    ): BadgeDataSource {
        return BadgeDataSourceImpl(retrofit)
    }
    @Provides
    @Singleton
    fun provideUserDataSource(
        retrofit: Retrofit
    ): UserDataSource {
        return UserDataSourceImpl(retrofit)
    }

    @Provides
    @Singleton
    fun provideDailyDataSource(
        retrofit: Retrofit
    ): DailyDataSource {
        return DailyDataSourceImpl(retrofit)
    }
    @Provides
    @Singleton
    fun provideRankingDataSource(
        retrofit: Retrofit
    ): RankingDataSource {
        return RankingDataSourceImpl(retrofit)
    }

    @Provides
    @Singleton
    fun provideFollowDataSource(
        retrofit: Retrofit
    ):FollowDataSource{
        return FollowDataSourceImpl(retrofit)
    }

    @Provides
    @Singleton
    fun provideCommonDataSource(
        retrofit: Retrofit
    ):CommonDataSource{
        return CommonDataSourceImpl(retrofit)
    }



}