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

    // Success result (keypass)
    private val _loginResult = MutableLiveData<Result<String>>()
    val loginResult: LiveData<Result<String>> = _loginResult

    // Error message for UI
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun login(location: String, username: String, password: String) {
        viewModelScope.launch {
            val result = repository.login(location, username, password)
            result.onSuccess {
                _loginResult.value = Result.success(it)
            }.onFailure { error ->
                val message = when {
                    error.message?.contains("404") == true -> "User not found. Please check your username or password."
                    error.message?.contains("401") == true -> "Unauthorized. Please try again."
                    else -> "Login failed. Please try again."
                }
                _errorMessage.value = message
            }
        }
    }
}
