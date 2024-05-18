package com.bangkitacademy.githubuserapp.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "username")
    var username: String = "",

    @ColumnInfo(name = "avatarUrl")
    var avatarUrl: String? = null,

    @ColumnInfo(name = "githubName")
    var urlHtml: String
)