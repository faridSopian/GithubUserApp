package com.bangkitacademy.githubuserapp.core.utils

import com.bangkitacademy.githubuserapp.core.data.source.local.entity.FavoriteEntity
import com.bangkitacademy.githubuserapp.core.data.source.remote.response.DetailUserResponse
import com.bangkitacademy.githubuserapp.core.data.source.remote.response.ItemsItem
import com.bangkitacademy.githubuserapp.core.domain.model.Favorite
import com.bangkitacademy.githubuserapp.core.domain.model.UserDetail
import com.bangkitacademy.githubuserapp.core.domain.model.UserItem

object DataMapper {

    fun mapEntitiesToDomain(input: List<FavoriteEntity>): List<Favorite> =
        input.map {
            Favorite(
                username = it.username,
                avatarUrl = it.avatarUrl,
                githubName = it.urlHtml
            )
        }
    fun mapDomainToEntity(input: Favorite) = FavoriteEntity(
        username = input.username,
        avatarUrl = input.avatarUrl,
        urlHtml = input.githubName
    )

    fun DetailUserResponse.toDomainModel(): UserDetail {
        return UserDetail(
            avatarUrl = this.avatarUrl,
            githubName = this.name,
            githubUsername = this.login,
            githubFollowing = this.following,
            githubFollowers = this.followers
        )
    }

    fun ItemsItem.toDomainModel(): UserItem {
        return UserItem(
            avatarUrl = this.avatarUrl,
            githubUsername = this.login,
            githubName = this.htmlUrl,
        )
    }
}