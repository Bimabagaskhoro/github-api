package com.bimabagaskhoro.asigmentkompas.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bimabagaskhoro.asigmentkompas.data.source.RemoteDataSource
import com.bimabagaskhoro.asigmentkompas.data.source.remote.response.ItemRepos
import com.bimabagaskhoro.asigmentkompas.data.source.remote.response.ItemsDetail
import com.bimabagaskhoro.asigmentkompas.data.source.remote.response.ItemsUser

class UserRepository private constructor(
    private val remoteDataSource: RemoteDataSource
) : IUserRepository {
    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(
            remote: RemoteDataSource
        ): UserRepository =
            instance ?: synchronized(this) {
                UserRepository(remote).apply {
                    instance = this
                }
            }
    }

    override fun loadSearchUser(q: String): LiveData<List<ItemsUser>> {

        val getSearchUser = MutableLiveData<List<ItemsUser>>()
        remoteDataSource.getSearchUser(object : RemoteDataSource.LoadSearchUserCallback {
            override fun onAllSearchUserReceived(userResponse: List<ItemsUser>?) {
                val listUser = ArrayList<ItemsUser>()
                if (userResponse != null) {
                    for (user in userResponse) {
                        user.apply {
                            val users = ItemsUser(
                                login, avatarUrl
                            )
                            listUser.add(users)
                        }
                    }
                    getSearchUser.postValue(listUser)
                }
            }
        }, q)
        return getSearchUser
    }

    override fun loadDetailUser(username: String): LiveData<ItemsDetail> {

        val getDetailUser = MutableLiveData<ItemsDetail>()
        remoteDataSource.getDetailUser(object : RemoteDataSource.LoadDetailUserCallback {
            override fun onAllDetailUserReceived(userDetail: ItemsDetail?) {
                lateinit var detailUser: ItemsDetail
                if (userDetail != null) {
                    userDetail?.apply {

                        detailUser = ItemsDetail(
                            login, avatarUrl, bio, name
                        )
                    }
                }


                getDetailUser.postValue(detailUser)
            }
        }, username)
        return getDetailUser
    }

    override fun loadRepos(username: String): LiveData<List<ItemRepos>> {

        val getRepos = MutableLiveData<List<ItemRepos>>()
        remoteDataSource.getReposUser(object : RemoteDataSource.LoadReposUserCallback {
            override fun onAllReposUserReceived(userRepos: List<ItemRepos>?) {
                val listRepo = ArrayList<ItemRepos>()
                if (userRepos != null) {
                    for (repos in listRepo) {
                        repos.apply {
                            val repository = ItemRepos(
                                login, updatedAt, stargazersCount, description, name
                            )
                            listRepo.add(repository)
                        }
                    }
                    getRepos.postValue(listRepo)
                }
            }

        }, username)
        return getRepos
    }


}