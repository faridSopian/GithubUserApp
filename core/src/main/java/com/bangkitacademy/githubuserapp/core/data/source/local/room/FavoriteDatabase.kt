package com.bangkitacademy.githubuserapp.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bangkitacademy.githubuserapp.core.data.source.local.entity.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 2)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun userDao(): FavoriteDao
}