package com.bimabagaskhoro.asigmentkompas.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bimabagaskhoro.asigmentkompas.adapter.UserAdapter
import com.bimabagaskhoro.asigmentkompas.databinding.FragmentMainBinding
import com.bimabagaskhoro.asigmentkompas.ui.ViewModelFactory
import com.bimabagaskhoro.asigmentkompas.ui.detail.DetailActivity

class MainFragment : Fragment(), UserAdapter.DataCallback {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var userAdapters: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            edtSearch.setOnKeyListener { _, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    searchUsers()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }
    }

    private fun searchUsers() {
        binding.apply {
            val search = edtSearch.text.toString()
            if (search.isEmpty()) return
            initViewModel(search)
        }
    }

    private fun initViewModel(search: String) {
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
            userAdapters = UserAdapter()
            viewModel.getSearchUser(search).observe(viewLifecycleOwner, { data ->
                userAdapters.apply {
                    setShow(data)
                    callback(this@MainFragment)
                }
                binding.rvGithubUser.apply {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = userAdapters
                }
            })

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClicked(username: String) {
        startActivity(Intent(context, DetailActivity::class.java)
            .putExtra(DetailActivity.EXTRA_DATA, username)
        )
    }
}