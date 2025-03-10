package com.example.gitrepoexplore.ui.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gitrepoexplore.databinding.ActivityMainBinding
import com.example.gitrepoexplore.ui.adapter.RepositoryAdapter
import com.example.gitrepoexplore.viewmodel.GitHubViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: GitHubViewModel by viewModels()
    private lateinit var adapter: RepositoryAdapter
    private lateinit var binding: ActivityMainBinding
    private var isLoading = false
    private var currentUsername: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        adapter = RepositoryAdapter(mutableListOf())
        binding.recyclerView.adapter = adapter

        binding.btnFetchRepos.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            if (username.isNotEmpty()) {
                currentUsername = username
                adapter.clearData()
                viewModel.fetchRepositories(username, isNewSearch = true)
            }
        }

        viewModel.repositories.observe(this) { repositories ->
            adapter.updateData(repositories)

        }

        viewModel.isLoading.observe(this) { loading ->
            binding.progressBar.visibility = if (loading) View.VISIBLE else View.GONE
            isLoading = loading
        }

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!isLoading && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount - 2) {
                    currentUsername?.let { viewModel.fetchRepositories(it) } // LOAD NEXT PAGE
                }
            }
        })
    }
}
