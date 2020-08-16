package com.example.hiltdemo.app

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.example.hiltdemo.app.useCase.DataUseCase
import com.example.hiltdemo.model.GithubDataModel
import com.example.hiltdemo.model.ResultData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow

class MainViewModel @ViewModelInject constructor(val dataUseCase: DataUseCase): ViewModel() {


    fun getRepoList() : LiveData<ResultData<GithubDataModel>> {
        return flow {
//            emit(ResultData.Loading(nothing))
            try {
                emit(dataUseCase.getReposList())
            } catch (e: Exception) {
                e.printStackTrace()
                emit(ResultData.Exception())
            }
        }.asLiveData(Dispatchers.IO)

    }
}