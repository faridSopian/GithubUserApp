package com.bangkitacademy.githubuserapp.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Favorite(
    val username: String,
    val avatarUrl: String?,
    val githubName: String
) : Parcelable