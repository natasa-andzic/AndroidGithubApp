package com.natasaandzic.androidgithubapp.di

import com.natasaandzic.androidgithubapp.mvvm.GitHubRepository
import com.natasaandzic.androidgithubapp.network.GitHubApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGithubRepository(api: GitHubApi) = GitHubRepository(api)

}