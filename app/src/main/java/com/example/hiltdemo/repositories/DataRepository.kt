package com.example.hiltdemo.repositories

import com.example.hiltdemo.model.GithubDataModel
import com.example.hiltdemo.network.ApiService
import javax.inject.Inject

class DataRepository @Inject constructor(val apiService: ApiService){
    suspend fun getPublicRepositories():GithubDataModel{
        return apiService.getPublicRepositories()
    }
}