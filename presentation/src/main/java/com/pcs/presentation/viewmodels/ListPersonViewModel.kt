package com.pcs.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pcs.domain.model.Person
import com.pcs.domain.usecase.GetPersonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListPersonViewModel @Inject constructor(
    private val getPersonUseCase: GetPersonUseCase
) : ViewModel() {

    private val _data = MutableLiveData<List<Person>?>(emptyList())
    val data: LiveData<List<Person>?> = _data

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean?> = _isLoading

    fun getPersons() {
        _isLoading.value = true
        viewModelScope.launch {
            val result = getPersonUseCase()
            _data.value = result
            _isLoading.value = false
        }
    }
}