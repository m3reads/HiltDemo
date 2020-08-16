package com.example.hiltdemo

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.hiltdemo.app.MainViewModel
import com.example.hiltdemo.app.adapter.RepoAdapter
import com.example.hiltdemo.model.ResultData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var repositoriesAdapter: RepoAdapter


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
                    //set loading UI state
                    setLoadingUIState(true)
                }
                is ResultData.Success -> {
                    setLoadingUIState(false)

                    val githubDataModel = results.data
                    adapter.submitList(githubDataModel)
                }
                is ResultData.Exception -> {
                    //handle exception
                }
                is ResultData.Failed-> {

                }
            }
        })
    }

    private fun setLoadingUIState(inLoadingState: Boolean) {
        if (inLoadingState)
            progress_circular.visibility = View.VISIBLE
        else
            progress_circular.visibility = View.GONE
    }
}