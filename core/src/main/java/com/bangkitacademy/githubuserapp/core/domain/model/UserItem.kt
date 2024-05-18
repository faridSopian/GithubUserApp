package com.bangkitacademy.githubuserapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@kotlinx.android.parcel.Parcelize
data class UserItem(
    val avatarUrl: String,
    val githubUsername: String,
    val githubName: String,
) : Parcelable