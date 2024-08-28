package com.pcs.domain.repository

import com.pcs.domain.model.Person

interface RemoteRepository {
    suspend fun getPersons(): List<Person>
}