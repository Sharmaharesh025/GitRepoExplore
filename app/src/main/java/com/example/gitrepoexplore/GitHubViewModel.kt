package com.example.gitrepoexplore

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GitHubViewModel @Inject constructor(private val repository: GitHubRepository) : ViewModel() {

    private val _repositories = MutableLiveData<List<Repository>>()
    val repositories: LiveData<List<Repository>> = _repositories

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var currentPage = 1
    private var isLastPage = false

    fun fetchRepositories(username: String) {
        if (isLoading.value == true || isLastPage) return

        _isLoading.postValue(true)

        viewModelScope.launch {
            try {
                val result = repository.getRepositories(username, currentPage)
                if (result.isNotEmpty()) {
                    val updatedList = (_repositories.value ?: emptyList()) + result
                    _repositories.postValue(updatedList)
                    currentPage++
                } else {
                    isLastPage = true
                }
            } catch (e: Exception) {
                Log.e("GitHubViewModel", "Error fetching repos: ${e.message}")
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}
