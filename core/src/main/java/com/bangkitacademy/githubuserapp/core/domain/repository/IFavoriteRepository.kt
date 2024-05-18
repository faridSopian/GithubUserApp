package com.bangkitacademy.githubuserapp.core.domain.repository

import com.bangkitacademy.githubuserapp.core.domain.model.Favorite
import kotlinx.coroutines.flow.Flow

interface IFavoriteRepository {
    fun getAllFavorite(): Flow<List<Favorite>>

    fun insertUser(user: Favorite)

    fun deleteUser(user: Favorite)

    fun getDataByUsername(username: String): Flow<List<Favorite>>
}