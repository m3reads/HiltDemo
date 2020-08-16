package com.example.hiltdemo.network

import com.example.hiltdemo.model.GithubDataModel
import retrofit2.http.GET

interface ApiService {
    @GET(NetworkConstants.URL_REPOSITORIES)
    suspend fun getPublicRepositories(): GithubDataModel
}