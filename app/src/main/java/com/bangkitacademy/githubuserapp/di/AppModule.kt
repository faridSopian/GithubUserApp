package com.bangkitacademy.githubuserapp.di

import com.bangkitacademy.githubuserapp.core.SettingPreferences
import com.bangkitacademy.githubuserapp.core.domain.usecase.FavoriteInteractor
import com.bangkitacademy.githubuserapp.core.domain.usecase.FavoriteUseCase
import com.bangkitacademy.githubuserapp.core.dataStore
import com.bangkitacademy.githubuserapp.core.domain.usecase.ThemeInteractor
import com.bangkitacademy.githubuserapp.core.domain.usecase.ThemeUseCase
import com.bangkitacademy.githubuserapp.detail.DetailViewModel
import com.bangkitacademy.githubuserapp.favorite.FavoriteViewModel
import com.bangkitacademy.githubuserapp.home.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<FavoriteUseCase> { FavoriteInteractor(get()) }
}

val viewModelModule = module {
    single { SettingPreferences.getInstance(androidApplication().dataStore) }

    single<ThemeUseCase> { ThemeInteractor(get()) }

    viewModel { DetailViewModel(get(), get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { MainViewModel(get(), get()) }
}