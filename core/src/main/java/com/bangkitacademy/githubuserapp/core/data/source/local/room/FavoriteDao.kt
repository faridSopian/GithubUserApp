package com.bangkitacademy.githubuserapp.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bangkitacademy.githubuserapp.core.data.source.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: FavoriteEntity)

    @Delete
    fun delete(user: FavoriteEntity)

    @Query("SELECT * FROM FavoriteEntity ORDER BY username ASC")
    fun getAllFavoriteData(): Flow<List<FavoriteEntity>>

    @Query("SELECT * FROM FavoriteEntity WHERE username = :username")
    fun getDataByUsername(username: String): Flow<List<FavoriteEntity>>
}