package com.bangkitacademy.githubuserapp.core.domain.usecase

import androidx.lifecycle.LiveData

interface ThemeUseCase {
    fun getThemeSetting(): LiveData<Boolean>
}