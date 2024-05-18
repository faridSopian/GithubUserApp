package com.bangkitacademy.githubuserapp.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkitacademy.githubuserapp.core.domain.usecase.FavoriteUseCase

class FavoriteViewModel(private val favUseCase: FavoriteUseCase) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getAllFavorite() = favUseCase.getAllFavorite().asLiveData()
}