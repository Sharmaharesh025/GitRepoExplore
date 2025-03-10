package com.example.gitrepoexplore.model

//data class Repository(
//    val id: Int,
//    val name: String,
//    val description: String?,
//    val language: String?,
//    val stargazers_count: Int,
//    val forks_count: Int
//)


import com.google.gson.annotations.SerializedName

data class Repository(
    val id: Int,
    @SerializedName("node_id") val nodeId: String,
    val name: String,
    @SerializedName("full_name") val fullName: String,
    val private: Boolean,
    val owner: Owner,
    @SerializedName("html_url") val htmlUrl: String,
    val description: String?,
    val fork: Boolean,
    val url: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("pushed_at") val pushedAt: String,
    @SerializedName("git_url") val gitUrl: String,
    @SerializedName("ssh_url") val sshUrl: String,
    @SerializedName("clone_url") val cloneUrl: String,
    @SerializedName("svn_url") val svnUrl: String,
    val homepage: String?,
    val size: Int,
    @SerializedName("stargazers_count") val stargazersCount: Int,
    @SerializedName("watchers_count") val watchersCount: Int,
    val language: String?,
    @SerializedName("has_issues") val hasIssues: Boolean,
    @SerializedName("has_projects") val hasProjects: Boolean,
    @SerializedName("has_downloads") val hasDownloads: Boolean,
    @SerializedName("has_wiki") val hasWiki: Boolean,
    @SerializedName("has_pages") val hasPages: Boolean,
    @SerializedName("forks_count") val forksCount: Int,
    val archived: Boolean,
    val disabled: Boolean,
    @SerializedName("open_issues_count") val openIssuesCount: Int,
    val license: License?,
    @SerializedName("allow_forking") val allowForking: Boolean,
    @SerializedName("is_template") val isTemplate: Boolean,
    val visibility: String,
    val forks: Int,
    @SerializedName("open_issues") val openIssues: Int,
    val watchers: Int,
    @SerializedName("default_branch") val defaultBranch: String
)

data class Owner(
    val login: String,
    val id: Int,
    @SerializedName("node_id") val nodeId: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    val url: String,
    @SerializedName("html_url") val htmlUrl: String
)

data class License(
    val key: String,
    val name: String,
    @SerializedName("spdx_id") val spdxId: String?,
    val url: String?,
    @SerializedName("node_id") val nodeId: String
)

