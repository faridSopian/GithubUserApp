package com.bangkitacademy.githubuserapp

import android.app.Application
import com.bangkitacademy.githubuserapp.core.di.databaseModule
import com.bangkitacademy.githubuserapp.core.di.networkModule
import com.bangkitacademy.githubuserapp.core.di.repositoryModule
import com.bangkitacademy.githubuserapp.di.useCaseModule
import com.bangkitacademy.githubuserapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}