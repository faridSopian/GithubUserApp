package com.bangkitacademy.githubuserapp.core.di

import androidx.room.Room
import com.bangkitacademy.githubuserapp.core.BuildConfig
import com.bangkitacademy.githubuserapp.core.data.FavoriteRepository
import com.bangkitacademy.githubuserapp.core.data.source.local.room.FavoriteDatabase
import com.bangkitacademy.githubuserapp.core.data.source.remote.retrofit.ApiService
import com.bangkitacademy.githubuserapp.core.domain.repository.IFavoriteRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val databaseModule = module {
    factory { get<FavoriteDatabase>().userDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            FavoriteDatabase::class.java, "github_db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        val authInterceptor = Interceptor { chain ->
            val req = chain.request()
            val requestHeaders = req.newBuilder()
                .addHeader("Authorization", "token ${BuildConfig.apiKey}")
                .build()
            chain.proceed(requestHeaders)
        }

        OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()

        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single<IFavoriteRepository>{ FavoriteRepository(get()) }
}