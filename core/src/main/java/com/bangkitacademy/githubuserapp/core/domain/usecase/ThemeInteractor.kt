package com.bangkitacademy.githubuserapp.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.bangkitacademy.githubuserapp.core.SettingPreferences

class ThemeInteractor(private val settingPreferences: SettingPreferences) : ThemeUseCase {
    override fun getThemeSetting(): LiveData<Boolean> {
        return settingPreferences.getThemeSetting().asLiveData()
    }
}