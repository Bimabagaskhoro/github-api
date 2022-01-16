package com.bimabagaskhoro.asigmentkompas.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bimabagaskhoro.asigmentkompas.data.repository.UserRepository
import com.bimabagaskhoro.asigmentkompas.data.source.remote.response.ItemRepos
import com.bimabagaskhoro.asigmentkompas.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RepositoryViewModelTest {
    private lateinit var viewModel: RepositoryViewModel

    private val dummyDetailUser = DataDummy.generateDummyRepos()[0]
    private val username = dummyDetailUser.login

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var usersObserver: Observer<List<ItemRepos>>

    @Before
    fun setUp() {
        viewModel = RepositoryViewModel(userRepository)
    }

    @Test
    fun getReposUser() {
        val dummyUser = DataDummy.generateDummyRepos()
        val users = MutableLiveData<List<ItemRepos>>()
        users.value = dummyUser

        `when`(userRepository.loadRepos(username)).thenReturn(users)
        val userRepos = viewModel.getReposUser(username).value
        verify(userRepository).loadRepos(username)
        assertNotNull(userRepos)
        assertEquals(1, userRepos?.size)

        viewModel.getReposUser(username).observeForever(usersObserver)
        verify(usersObserver).onChanged(dummyUser)
    }
}