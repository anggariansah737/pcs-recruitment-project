package com.pcs.data.di

import com.pcs.data.api.ApiService
import com.pcs.domain.repository.RemoteRepository
import com.pcs.data.repository.RemoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRemoteRepository(
        apiService: ApiService
    ): RemoteRepository = RemoteRepositoryImpl(apiService)
}