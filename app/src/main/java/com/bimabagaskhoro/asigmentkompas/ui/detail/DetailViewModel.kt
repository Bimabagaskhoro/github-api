package com.bimabagaskhoro.asigmentkompas.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bimabagaskhoro.asigmentkompas.data.repository.UserRepository
import com.bimabagaskhoro.asigmentkompas.data.source.remote.response.ItemsDetail

class DetailViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun getDetailUser(username: String): LiveData<ItemsDetail> = userRepository.loadDetailUser(username)
}