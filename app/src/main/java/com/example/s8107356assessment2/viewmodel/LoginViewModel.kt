package com.example.s8107356assessment2.viewmodel

import androidx.lifecycle.*
import com.example.s8107356assessment2.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _loginResult = MutableLiveData<Result<String>>()
    val loginResult: LiveData<Result<String>> = _loginResult

    fun login(location: String, username: String, password: String) {
        viewModelScope.launch {
            val result = repository.login(location, username, password)
            _loginResult.value = result
        }
    }
}
