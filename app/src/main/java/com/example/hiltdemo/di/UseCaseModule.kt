package com.example.hiltdemo.di

import com.example.hiltdemo.app.useCase.DataUseCase
import com.example.hiltdemo.repositories.DataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@InstallIn(ActivityRetainedComponent::class)
@Module
object UseCaseModule{

    @Provides
    fun getUseCase(dataRepository: DataRepository): DataUseCase{
        return DataUseCase(dataRepository)
    }
}