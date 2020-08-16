package com.example.hiltdemo.app.useCase

import com.example.hiltdemo.model.GithubDataModel
import com.example.hiltdemo.model.ResultData
import com.example.hiltdemo.repositories.DataRepository
import javax.inject.Inject

class DataUseCase @Inject constructor(val dataRepository: DataRepository) {
    suspend fun getReposList():ResultData<GithubDataModel>{
        val repoList = dataRepository.getPublicRepositories()

        val resultData = if (!repoList.isNullOrEmpty()){
             ResultData.Success(repoList)
        }else{
             ResultData.Failed("Something Suspicious!!")
        }
        return resultData
    }
}