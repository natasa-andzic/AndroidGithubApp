package com.natasaandzic.androidgithubapp.network

import com.natasaandzic.androidgithubapp.Constants
import com.natasaandzic.androidgithubapp.model.Repo
import com.natasaandzic.androidgithubapp.model.Tag
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Singleton

@Singleton
interface GitHubApi {

    @GET("users/{user}/repos")
    suspend fun getUserRepos(@Path("user") user: String = Constants.USERNAME): List<Repo>

    @GET("repos/{user}/{repo}")
    suspend fun getUserRepo(
        @Path("user") user: String = Constants.USERNAME,
        @Path("repo") repo: String
    ): Repo

    @GET("repos/{user}/{repo}/tags")
    suspend fun getRepoTags(
        @Path("user") user: String = Constants.USERNAME,
        @Path("repo") repo: String
    ): List<Tag>
}