package com.example.gitrepoexplore

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitHubRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getRepositories(username: String, page: Int): List<Repository> {
        return apiService.getRepositories(username, page)
    }
}
