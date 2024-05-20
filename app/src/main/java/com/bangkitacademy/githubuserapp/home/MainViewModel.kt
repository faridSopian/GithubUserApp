package com.bangkitacademy.githubuserapp.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkitacademy.githubuserapp.core.data.source.remote.retrofit.ApiService
import com.bangkitacademy.githubuserapp.core.domain.model.UserItem
import com.bangkitacademy.githubuserapp.core.domain.usecase.ThemeUseCase
import com.bangkitacademy.githubuserapp.core.utils.DataMapper.toDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainViewModel(private val apiService: ApiService, private val themeUseCase: ThemeUseCase): ViewModel() {

    private val _listGithub = MutableLiveData<List<UserItem>>()
    val listGithub: LiveData<List<UserItem>> = _listGithub

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "MainViewModel"
    }

    init {
        fetchGithubUsers("john")
    }

    suspend fun searchGithubParamName(query: String): Flow<List<UserItem>> = flow {
        try {
            val response = apiService.getGithub(query)
            val domainItems = response.items.map { it.toDomainModel() }
            emit(domainItems)
        } catch (e: Exception) {
            Log.e(TAG, "onFailure: ${e.message.toString()}")
        }
    }

    fun fetchGithubUsers(query: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                searchGithubParamName(query).collect { items ->
                    _listGithub.value = items
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getTheme(): LiveData<Boolean> {
        return themeUseCase.getThemeSetting()
    }

//    class ViewModelFactory(private val apiService: ApiService, private val themeUseCase: ThemeUseCase) : ViewModelProvider.NewInstanceFactory() {
//        @Suppress("UNCHECKED_CAST")
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
//                return MainViewModel(apiService, themeUseCase) as T
//            }
//            throw IllegalArgumentException("Unknown ViewModel Class: " + modelClass.name)
//        }
//    }
}