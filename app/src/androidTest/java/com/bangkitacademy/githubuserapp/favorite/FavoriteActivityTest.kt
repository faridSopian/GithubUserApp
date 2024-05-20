package com.bangkitacademy.githubuserapp.favorite

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bangkitacademy.githubuserapp.core.data.source.local.entity.FavoriteEntity
import com.bangkitacademy.githubuserapp.core.data.source.local.room.FavoriteDao
import com.bangkitacademy.githubuserapp.core.data.source.local.room.FavoriteDatabase
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FavoriteActivityTest: TestCase(){
    private lateinit var favDb: FavoriteDatabase
    private lateinit var favoriteDao: FavoriteDao

    @Before
    fun setup(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        favDb = Room.inMemoryDatabaseBuilder(context, FavoriteDatabase::class.java).build()
        favoriteDao = favDb.userDao()
    }

    @After
    fun close(){
        favDb
    }

    @Test
    fun writeFavorite() = runBlocking{
        val entity = FavoriteEntity("john", "https://avatars.githubusercontent.com/u/1668?v=4", "https://github.com/john")
        favoriteDao.insert(entity)
    }
}