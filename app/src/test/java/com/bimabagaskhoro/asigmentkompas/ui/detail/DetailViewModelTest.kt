package com.bimabagaskhoro.asigmentkompas.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bimabagaskhoro.asigmentkompas.data.repository.UserRepository
import com.bimabagaskhoro.asigmentkompas.data.source.remote.response.ItemsDetail
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
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel

    private val dummyDetailUser = DataDummy.generateDummyDetailUser()
    private val username = dummyDetailUser.login

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var userObserver: Observer<ItemsDetail>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(userRepository)
    }

    
    @Test
    fun getDetailUser() {
        val users = MutableLiveData<ItemsDetail>()
        users.value = dummyDetailUser

        `when`(userRepository.loadDetailUser(username)).thenReturn(users)
        viewModel.setDetailUser(username)
        val detailUser = viewModel.getDetailUser().value
        verify(userRepository).loadDetailUser(username)
        assertNotNull(detailUser)

        dummyDetailUser.apply {
            assertEquals(login, detailUser?.login)
            assertEquals(avatarUrl, detailUser?.avatarUrl)
            assertEquals(bio, detailUser?.bio)
            assertEquals(name, detailUser?.name)
        }

        viewModel.getDetailUser().observeForever(userObserver)
        verify(userObserver).onChanged(dummyDetailUser)

    }

}