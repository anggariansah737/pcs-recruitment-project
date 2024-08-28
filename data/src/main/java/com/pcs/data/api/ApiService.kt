package com.pcs.data.api

import com.pcs.domain.model.Person
import retrofit2.http.GET

interface ApiService {

    @GET("getData/test")
    suspend fun getPersons(): List<Person>
}