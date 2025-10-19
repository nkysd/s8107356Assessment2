package com.example.s8107356assessment2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.s8107356assessment2.model.Entity
import com.example.s8107356assessment2.repository.DashboardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: DashboardRepository
) : ViewModel() {

    private val _entities = MutableLiveData<List<Entity>>()
    val entities: LiveData<List<Entity>> = _entities

    private val _error = MutableLiveData<Throwable?>()
    val error: LiveData<Throwable?> = _error

    fun fetchDashboardData(keypass: String) {
        viewModelScope.launch {
            val result = repository.getDashboardData(keypass)
            result.onSuccess { list ->
                _entities.value = list
                _error.value = null
            }
            result.onFailure { exception ->
                _error.value = exception
            }
        }
    }
}
