package com.example.hiltdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.hiltdemo.app.MainViewModel
import com.example.hiltdemo.app.adapter.RepoAdapter
import com.example.hiltdemo.model.ResultData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RepoAdapter()
        rv_items.adapter = adapter

        val list = mainViewModel.getRepoList()
        list.observe(viewLifecycleOwner, Observer { results->
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