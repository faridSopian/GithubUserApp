package com.bangkitacademy.githubuserapp.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserDetail(
    val avatarUrl: String,
    val githubName: String,
    val githubUsername: String,
    val githubFollowing: Int,
    val githubFollowers: Int
) : Parcelable