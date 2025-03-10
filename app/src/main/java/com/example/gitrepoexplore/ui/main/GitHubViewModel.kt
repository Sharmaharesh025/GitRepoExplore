package com.example.gitrepoexplore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitrepoexplore.model.Repository
import com.example.gitrepoexplore.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GitHubViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    private val _repositories = MutableLiveData<List<Repository>>()
    val repositories: LiveData<List<Repository>> = _repositories

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var currentPage = 1
    private var isLastPage = false
    private var currentUsername: String? = null
    private val repoList = mutableListOf<Repository>()

    fun fetchRepositories(username: String, isNewSearch: Boolean = false) {
        if (isNewSearch) {
            currentPage = 1
            isLastPage = false
            repoList.clear()
            currentUsername = username
        }

        if (isLastPage || _isLoading.value == true) return

        _isLoading.value = true
        viewModelScope.launch {
            try {
                val newRepos = apiService.getRepositories(username, currentPage)
                if (newRepos.isEmpty()) {
                    isLastPage = true
                } else {
                    repoList.addAll(newRepos)
                    _repositories.value = repoList
                    currentPage++
                }
            } catch (e: Exception) {
                // Handle error
            } finally {
                _isLoading.value = false
            }
        }
    }
}
