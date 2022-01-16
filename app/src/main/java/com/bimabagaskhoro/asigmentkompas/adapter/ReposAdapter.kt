package com.bimabagaskhoro.asigmentkompas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bimabagaskhoro.asigmentkompas.data.source.remote.response.ItemRepos
import com.bimabagaskhoro.asigmentkompas.data.source.remote.response.ItemsUser
import com.bimabagaskhoro.asigmentkompas.databinding.ItemsRepositoryGithubBinding

class ReposAdapter : RecyclerView.Adapter<ReposAdapter.ViewHolder>() {
    private var items = ArrayList<ItemRepos>()
    fun setRepos(repos: List<ItemRepos>) {
        this.items.clear()
        this.items.addAll(repos)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemsRepositoryGithubBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: ItemsRepositoryGithubBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ItemRepos) {
            binding.apply {
                tvNameRepository.text = data.name
                tvDescriptionRepository.text = data.description
                tvStar.text = data.stargazersCount.toString()
                tvLastUpdated.text = data.updatedAt
            }
        }
    }

}