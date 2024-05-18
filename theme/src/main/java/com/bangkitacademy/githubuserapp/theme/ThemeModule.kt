package com.bangkitacademy.githubuserapp.theme

import androidx.lifecycle.ViewModelProvider
import com.bangkitacademy.githubuserapp.core.SettingPreferences
import com.bangkitacademy.githubuserapp.core.dataStore
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val themeModule = module {
    // Menyediakan SettingPreferences
    single(override = true) { SettingPreferences.getInstance(androidApplication().dataStore) }

    // Menyediakan ViewModelFactory
    single<ViewModelProvider.Factory> { ThemeViewModel.ViewModelFactory(get()) }

    viewModel { ThemeViewModel(get()) }
}