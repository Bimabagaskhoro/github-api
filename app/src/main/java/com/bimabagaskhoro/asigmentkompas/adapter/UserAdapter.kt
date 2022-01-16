package com.bimabagaskhoro.asigmentkompas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bimabagaskhoro.asigmentkompas.R
import com.bimabagaskhoro.asigmentkompas.data.source.remote.response.ItemsUser
import com.bimabagaskhoro.asigmentkompas.databinding.ItemsGithubBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private lateinit var callback: DataCallback
    private var items = ArrayList<ItemsUser>()
    fun setShow(user: List<ItemsUser>) {
        this.items.clear()
        this.items.addAll(user)
        notifyDataSetChanged()
    }

    fun callback(callback: DataCallback) {
        this.callback = callback
    }

    interface DataCallback  {
        fun onItemClicked(username: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemsGithubBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: ItemsGithubBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ItemsUser) {
            binding.apply {
                Glide.with(itemView)
                    .load(data.avatarUrl)
                    .apply(
                        RequestOptions.placeholderOf(R.color.grey)
                    )
                    .into(imgProfile)
                tvUsername.text = data.login
                itemView.setOnClickListener {
                    callback.onItemClicked(data.login)
                }
            }

        }
    }

}