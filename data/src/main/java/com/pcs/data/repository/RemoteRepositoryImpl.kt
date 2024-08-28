package com.pcs.data.repository

import com.pcs.data.api.ApiService
import com.pcs.domain.model.Person
import com.pcs.domain.repository.RemoteRepository
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : RemoteRepository {

    override suspend fun getPersons(): List<Person> {
        return apiService.getPersons()
    }
}