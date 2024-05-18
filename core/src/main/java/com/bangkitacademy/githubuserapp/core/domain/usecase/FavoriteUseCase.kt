package com.bangkitacademy.githubuserapp.core.domain.usecase

import com.bangkitacademy.githubuserapp.core.domain.model.Favorite
import kotlinx.coroutines.flow.Flow

interface FavoriteUseCase {
    fun getAllFavorite(): Flow<List<Favorite>>

    fun insertUser(user: Favorite)

    fun deleteUser(user: Favorite)

    fun getDataByUsername(username: String): Flow<List<Favorite>>
}