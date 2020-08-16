package com.example.hiltdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.hiltdemo.app.MainViewModel
import com.example.hiltdemo.app.adapter.RepoAdapter
import com.example.hiltdemo.model.GithubDataModel
import com.example.hiltdemo.model.ResultData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var repositoriesAdapter: RepoAdapter
    private val repositoryObserver = Observer<ResultData<GithubDataModel?>> { resultData ->
        when(resultData) {
            is ResultData.Loading -> {

            }
            is ResultData.Success -> {
                val repositoriesModel = resultData.data
                repositoriesAdapter.submitList(repositoriesModel)
            }
            is ResultData.Failed -> {
            }
            is ResultData.Exception -> {
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {

        val adapter = RepoAdapter()
        rv_items.adapter = adapter

        val list = mainViewModel.getRepoList()
        list.observe(this, Observer { results->
            when(results){
                is ResultData.Loading -> {

                }
                is ResultData.Success -> {
                    val githubDataModel = results.data

                    adapter.submitList(githubDataModel)
                }
                is ResultData.Exception -> {

                }
                is ResultData.Failed-> {

                }
            }
        })
    }
}