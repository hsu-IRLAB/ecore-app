package com.hsu_irlab.ecore.di

import com.hsu_irlab.data.BadgeDataSourceImpl
import com.hsu_irlab.data.remote.BadgeDataSource
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
    ):BadgeDataSource{
        return BadgeDataSourceImpl(retrofit)
    }
}