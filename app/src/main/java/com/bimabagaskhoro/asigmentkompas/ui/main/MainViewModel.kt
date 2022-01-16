package com.bimabagaskhoro.asigmentkompas.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bimabagaskhoro.asigmentkompas.data.repository.UserRepository
import com.bimabagaskhoro.asigmentkompas.data.source.remote.response.ItemsUser

class MainViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun getSearchUser(q: String): LiveData<List<ItemsUser>> = userRepository.loadSearchUser(q)
}