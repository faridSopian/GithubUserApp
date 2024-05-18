package com.bangkitacademy.githubuserapp.core.data

import com.bangkitacademy.githubuserapp.core.data.source.local.room.FavoriteDao
import com.bangkitacademy.githubuserapp.core.domain.model.Favorite
import com.bangkitacademy.githubuserapp.core.domain.repository.IFavoriteRepository
import com.bangkitacademy.githubuserapp.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepository(private val favDao: FavoriteDao) : IFavoriteRepository {
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    override fun getAllFavorite(): Flow<List<Favorite>> = favDao.getAllFavoriteData().map { DataMapper.mapEntitiesToDomain(it) }

    override fun insertUser(user: Favorite) {
        val userMap = DataMapper.mapDomainToEntity(user)
        executorService.execute {
            favDao.insert(userMap)
        }
    }

    override fun deleteUser(user: Favorite) {
        val userMap = DataMapper.mapDomainToEntity(user)
        executorService.execute {
            favDao.delete(userMap)
        }
    }

    override fun getDataByUsername(username: String): Flow<List<Favorite>> = favDao.getDataByUsername(username).map { DataMapper.mapEntitiesToDomain(it) }
}