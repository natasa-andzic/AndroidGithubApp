package com.natasaandzic.androidgithubapp.mvvm

import com.natasaandzic.androidgithubapp.model.Repo
import com.natasaandzic.androidgithubapp.model.Tag

data class State<T>(
    val loading: Boolean = true,
    val data: T? = null,
    val error: String? = null
)

typealias RepoState = State<Repo>
typealias TagState = State<List<Tag>>
typealias DataState = State<List<Repo>>
