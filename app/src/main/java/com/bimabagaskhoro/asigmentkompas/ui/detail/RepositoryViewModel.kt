package com.bimabagaskhoro.asigmentkompas.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bimabagaskhoro.asigmentkompas.data.repository.UserRepository
import com.bimabagaskhoro.asigmentkompas.data.source.remote.response.ItemRepos

class RepositoryViewModel (private val userRepository: UserRepository) : ViewModel(){
    // view model repository
    fun getReposUser(username: String): LiveData<List<ItemRepos>> = userRepository.loadRepos(username)

}