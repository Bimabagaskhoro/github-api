package com.bimabagaskhoro.asigmentkompas.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bimabagaskhoro.asigmentkompas.R
import com.bimabagaskhoro.asigmentkompas.adapter.ReposAdapter
import com.bimabagaskhoro.asigmentkompas.data.source.RemoteDataSource
import com.bimabagaskhoro.asigmentkompas.data.source.remote.response.ItemRepos
import com.bimabagaskhoro.asigmentkompas.data.source.remote.response.ItemsDetail
import com.bimabagaskhoro.asigmentkompas.databinding.ActivityDetailBinding
import com.bimabagaskhoro.asigmentkompas.ui.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@Suppress("CAST_NEVER_SUCCEEDS")
class DetailActivity : AppCompatActivity() {
    private val TAG = DetailActivity::class.java.simpleName
    private lateinit var binding: ActivityDetailBinding
    private lateinit var reposAdapters: ReposAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getData()
    }

    private fun getData() {
        val extras = intent.extras
        if (extras != null) {
            val username = extras.getString(EXTRA_DATA)
            if (username != null) {
                initViewModel(username)
                initViewModelRepos(username)
            } else {
                Log.d(TAG, "No Username")
            }
            val bundle = Bundle()
            bundle.putString(EXTRA_DATA, username)
        }

    }

    private fun initViewModelRepos(username: String) {
        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[RepositoryViewModel::class.java]

        reposAdapters = ReposAdapter()
        viewModel.getReposUser(username).observe(this, { data ->
            reposAdapters.apply {
                setRepos(data)
            }
            showLoading(false)
            binding.apply {
                rvRepositoryGithub.layoutManager = LinearLayoutManager(this@DetailActivity)
                rvRepositoryGithub.setHasFixedSize(true)
                rvRepositoryGithub.adapter = reposAdapters
            }
        })
    }

    private fun initViewModel(username: String) {
        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
        viewModel.getDetailUser(username).observe(this, { detail ->
            if (detail != null){
                dataDetail(detail)
            }
        })
    }

    private fun dataDetail(detail: ItemsDetail?) {
        binding.apply {
            tvName.text = detail?.name
            tvUsernameDetail.text = detail?.login
            tvBio.text = detail?.bio
            Glide.with(this@DetailActivity)
                .load(detail?.avatarUrl)
                .apply(RequestOptions.placeholderOf(R.color.grey))
                .into(imgProfileDetail)
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.apply {
                progressbar.visibility = View.VISIBLE
                rvRepositoryGithub.visibility = View.INVISIBLE
            }
        } else {
            binding.apply {
                progressbar.visibility = View.INVISIBLE
                rvRepositoryGithub.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}