package com.bimabagaskhoro.asigmentkompas.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bimabagaskhoro.asigmentkompas.data.repository.UserRepository
import com.bimabagaskhoro.asigmentkompas.data.source.remote.response.ItemsUser
import com.bimabagaskhoro.asigmentkompas.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    private lateinit var viewModel: MainViewModel

    private val dummyDetailUser = DataDummy.generateDummyUser()[0]
    private val username = dummyDetailUser.login

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var usersObserver: Observer<List<ItemsUser>>

    @Before
    fun setUp() {
        viewModel = MainViewModel(userRepository)
    }

    @Test
    fun getSearchUser() {
        val dummyUser = DataDummy.generateDummyUser()
        val users = MutableLiveData<List<ItemsUser>>()
        users.value = dummyUser

        `when`(userRepository.loadSearchUser(username)).thenReturn(users)
        val userSearch = viewModel.getSearchUser(username).value
        verify(userRepository).loadSearchUser(username)
        assertNotNull(userSearch)
        assertEquals(1, userSearch?.size)

        viewModel.getSearchUser(username).observeForever(usersObserver)
        verify(usersObserver).onChanged(dummyUser)
    }
}