package com.bimabagaskhoro.asigmentkompas.data.source

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.bimabagaskhoro.asigmentkompas.data.source.remote.network.ApiClient
import com.bimabagaskhoro.asigmentkompas.data.source.remote.response.*
import com.bimabagaskhoro.asigmentkompas.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    companion object {
        const val TAG = "Remote Data Resource"

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource = instance ?: synchronized(this) {
            RemoteDataSource().apply { instance = this }
        }
    }

    fun getSearchUser(callback: LoadSearchUserCallback, q: String) {
        EspressoIdlingResource.increment()
        ApiClient().apiInstance().getSearchUsers(q)
            .enqueue(object : Callback<ResponseUser> {
                override fun onResponse(
                    call: Call<ResponseUser>,
                    response: Response<ResponseUser>
                ) {
                    if (response.isSuccessful){
                        callback.onAllSearchUserReceived(response.body()?.items)
                        EspressoIdlingResource.decrement()
                    }
                }

                override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                    Log.e(TAG, "Failure to search User ${t.message}")
                    EspressoIdlingResource.decrement()
                }

            })
    }

    fun getDetailUser(callback: LoadDetailUserCallback, username: String) {
        EspressoIdlingResource.increment()
        ApiClient().apiInstance().getUserDetail(username)
            .enqueue(object : Callback<ItemsDetail> {
                override fun onResponse(call: Call<ItemsDetail>, response: Response<ItemsDetail>) {
                    if (response.isSuccessful){
                        callback.onAllDetailUserReceived(response.body())
                        EspressoIdlingResource.decrement()
                    }
                }

                override fun onFailure(call: Call<ItemsDetail>, t: Throwable) {
                    Log.e(TAG, "Failure to Get Detail User ${t.message}")
                    EspressoIdlingResource.decrement()
                }
            })
    }


    fun getReposUser(callback: LoadReposUserCallback, username: String) {
        EspressoIdlingResource.increment()
        ApiClient().apiInstance().getUserRepository(username)
            .enqueue(object : Callback<List<ItemRepos>> {
                override fun onResponse(
                    call: Call<List<ItemRepos>>,
                    response: Response<List<ItemRepos>>
                ) {
                    if (response.isSuccessful){
                        callback.onAllReposUserReceived(response.body())
                        EspressoIdlingResource.decrement()
                    }
                }

                override fun onFailure(call: Call<List<ItemRepos>>, t: Throwable) {
                    Log.e(TAG, "Failure to Get Repos User ${t.message}")
                    EspressoIdlingResource.decrement()
                }

            })
    }

    interface LoadSearchUserCallback {
        fun onAllSearchUserReceived(userResponse: List<ItemsUser>?)
    }

    interface LoadDetailUserCallback {
        fun onAllDetailUserReceived(userDetail: ItemsDetail?)
    }

    interface LoadReposUserCallback {
        fun onAllReposUserReceived(userRepos: List<ItemRepos>?)
    }
}