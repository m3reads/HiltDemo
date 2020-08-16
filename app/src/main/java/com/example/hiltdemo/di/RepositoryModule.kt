package com.example.hiltdemo.di

import com.example.hiltdemo.network.ApiService
import com.example.hiltdemo.repositories.DataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@InstallIn(ActivityRetainedComponent::class)
@Module
object RepositoryModule {
    @Provides
    fun getDataRepository(apiService: ApiService): DataRepository {
        return DataRepository(apiService)
    }
}