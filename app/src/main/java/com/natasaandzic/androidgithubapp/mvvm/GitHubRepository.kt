package com.natasaandzic.androidgithubapp.mvvm

import com.natasaandzic.androidgithubapp.model.Repo
import com.natasaandzic.androidgithubapp.model.Tag
import com.natasaandzic.androidgithubapp.network.GitHubApi
import javax.inject.Inject

class GitHubRepository @Inject constructor(private val api: GitHubApi) {

    suspend fun getUserRepos(): List<Repo> {
        return api.getUserRepos()
    }

    suspend fun getUserRepo(username: String, repoName: String): Repo {
        return api.getUserRepo(username, repoName)
    }

    suspend fun getRepoTags(username: String, repoName: String): List<Tag> {
        return api.getRepoTags(username, repoName)
    }
}