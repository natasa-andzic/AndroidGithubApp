package com.natasaandzic.androidgithubapp.model

import com.google.gson.annotations.SerializedName

data class Repo(
    val name: String,
    @SerializedName("forks_count")
    val forksCount: Int,
    @SerializedName("watchers_count")
    val watchersCount: Int,
    @SerializedName("tags_url")
    val tagsUrl: String,
    @SerializedName("owner")
    val user: User,
    @SerializedName("open_issues_count")
    val issuesCount: Int
)
