package com.natasaandzic.androidgithubapp.model

data class Tag(
    val name: String,
    val commit: Commit
)

data class Commit(
    val sha: String
)
