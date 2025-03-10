package com.example.gitrepoexplore

data class Repository(
    val id: Int,
    val name: String,
    val description: String?,
    val language: String?,
    val stargazers_count: Int,
    val forks_count: Int
)

