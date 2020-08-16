package com.example.hiltdemo.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hiltdemo.R
import com.example.hiltdemo.model.DiffUtilGithubDataModelItem
import com.example.hiltdemo.model.GithubDataModel
import kotlinx.android.synthetic.main.row_repo_item.view.*

class RepoAdapter:ListAdapter<GithubDataModel.GithubDataModelItem,ViewHolder>(
    DiffUtilGithubDataModelItem()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_repo_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: GithubDataModel.GithubDataModelItem){
        itemView.tv_repo_name.text = item.name
        itemView.tv_repo_desc.text = item.description
    }
}