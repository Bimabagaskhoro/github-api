package com.bimabagaskhoro.asigmentkompas.data.repository

import androidx.lifecycle.LiveData
import com.bimabagaskhoro.asigmentkompas.data.source.remote.response.ItemRepos
import com.bimabagaskhoro.asigmentkompas.data.source.remote.response.ItemsDetail
import com.bimabagaskhoro.asigmentkompas.data.source.remote.response.ItemsUser

interface IUserRepository {

    fun loadSearchUser(q: String): LiveData<List<ItemsUser>>

    fun loadDetailUser(username: String): LiveData<ItemsDetail>

    fun loadRepos(username: String): LiveData<List<ItemRepos>>
}