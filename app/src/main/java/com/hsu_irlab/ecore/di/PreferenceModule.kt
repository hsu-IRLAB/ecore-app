package com.hsu_irlab.ecore.di

import android.content.Context
import android.content.SharedPreferences
import com.hsu_irlab.data.Prefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object PreferenceModule {

    @Provides
    @Singleton
    fun providePrefs(prefs: SharedPreferences):Prefs {
        return Prefs(prefs)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext appContext: Context): SharedPreferences {
        return appContext.getSharedPreferences("ECORE_PREF", Context.MODE_PRIVATE)
    }
}