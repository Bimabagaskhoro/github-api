package com.bimabagaskhoro.asigmentkompas.di

import android.content.Context
import com.bimabagaskhoro.asigmentkompas.data.repository.UserRepository
import com.bimabagaskhoro.asigmentkompas.data.source.RemoteDataSource

object Injection {

    fun provideRepository(context: Context): UserRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return UserRepository.getInstance(remoteDataSource)
    }
}