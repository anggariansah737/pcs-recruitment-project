package com.pcs.domain.usecase

import com.pcs.domain.model.Person
import com.pcs.domain.repository.RemoteRepository
import javax.inject.Inject

class GetPersonUseCase @Inject constructor(
    private val repository: RemoteRepository
) {
    suspend operator fun invoke(): List<Person> {
        return repository.getPersons()
    }
}