package com.bimabagaskhoro.asigmentkompas.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bimabagaskhoro.asigmentkompas.data.repository.UserRepository
import com.bimabagaskhoro.asigmentkompas.data.source.remote.response.ItemsDetail

class DetailViewModel(private val userRepository: UserRepository) : ViewModel() {

    // view model detail user
    private lateinit var detailUser: LiveData<ItemsDetail>
    fun setDetailUser(username: String) {
        detailUser = userRepository.loadDetailUser(username)
    }

    fun getDetailUser() = detailUser
}