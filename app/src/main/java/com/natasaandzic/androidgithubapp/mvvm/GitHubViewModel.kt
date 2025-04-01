package com.natasaandzic.androidgithubapp.mvvm

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GitHubViewModel @Inject constructor(private val repository: GitHubRepository) : ViewModel() {

    private val _repoState = mutableStateOf<RepoState>(State())
    val repoState: State<RepoState> = _repoState

    private val _tagState = mutableStateOf<TagState>(State())
    val tagState: State<TagState> = _tagState

    private val _dataState = mutableStateOf<DataState>(State())
    val dataState: State<DataState> = _dataState

    init {
        getUserRepos()
    }

    private fun getUserRepos() {
        viewModelScope.launch {
            try {
                val response = repository.getUserRepos()
                _dataState.value = _dataState.value.copy(
                    data = response,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _dataState.value = _dataState.value.copy(
                    loading = false,
                    error = "Error fetching repos ${e.message}"
                )
            }
        }
    }

    fun getUserRepo(username: String, repoName: String) {
        viewModelScope.launch {
            try {
                val response = repository.getUserRepo(username, repoName)
                _repoState.value = _repoState.value.copy(
                    data = response,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _repoState.value = _repoState.value.copy(
                    loading = false,
                    error = "Error fetching repo ${e.message}"
                )
            }
        }
    }

    fun getRepoTags(username: String, repoName: String) {
        viewModelScope.launch {
            try {
                val response = repository.getRepoTags(username, repoName)
                _tagState.value = _tagState.value.copy(
                    data = response,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _tagState.value = _tagState.value.copy(
                    loading = false,
                    error = "Error fetching tags ${e.message}"
                )
            }
        }
    }

}