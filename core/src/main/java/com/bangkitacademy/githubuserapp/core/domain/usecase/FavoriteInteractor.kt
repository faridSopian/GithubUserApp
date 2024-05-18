package com.bangkitacademy.githubuserapp.core.domain.usecase

import com.bangkitacademy.githubuserapp.core.domain.model.Favorite
import com.bangkitacademy.githubuserapp.core.domain.repository.IFavoriteRepository
import kotlinx.coroutines.flow.Flow

class FavoriteInteractor(private val favoriteRepository: IFavoriteRepository): FavoriteUseCase {
    override fun getAllFavorite(): Flow<List<Favorite>> = favoriteRepository.getAllFavorite()

    override fun insertUser(user: Favorite) = favoriteRepository.insertUser(user)

    override fun deleteUser(user: Favorite) = favoriteRepository.deleteUser(user)

    override fun getDataByUsername(username: String): Flow<List<Favorite>> = favoriteRepository.getDataByUsername(username)
}