package com.bangkitacademy.githubuserapp.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bangkitacademy.githubuserapp.core.data.source.remote.retrofit.ApiService
import com.bangkitacademy.githubuserapp.core.domain.model.Favorite
import com.bangkitacademy.githubuserapp.core.domain.model.UserDetail
import com.bangkitacademy.githubuserapp.core.domain.model.UserItem
import com.bangkitacademy.githubuserapp.core.domain.usecase.FavoriteUseCase
import com.bangkitacademy.githubuserapp.core.utils.DataMapper.toDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class DetailViewModel(private val apiService: ApiService, private val favUseCase: FavoriteUseCase): ViewModel() {

    companion object {
        private const val TAG = "DetailViewModel"
    }

    private val _detailGithubUser = MutableLiveData<UserDetail>()
    val detailGithubUser: LiveData<UserDetail> = _detailGithubUser

    private val _followers = MutableLiveData<List<UserItem>>()
    val followers: LiveData<List<UserItem>> = _followers

    private val _followings = MutableLiveData<List<UserItem>>()
    val followings: LiveData<List<UserItem>> = _followings

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun insertFav(user: Favorite) =
        favUseCase.insertUser(user)


    fun deleteFav(user: Favorite) =
        favUseCase.deleteUser(user)

    fun getFavByUsername(username: String) = favUseCase.getDataByUsername(username).asLiveData()

    suspend fun getDetailGithubUser(username: String): Flow<UserDetail> = flow{
        try {
            val response = apiService.getDetailUser(username)
            emit(response.toDomainModel())
        } catch (e: Exception) {
            Log.e(TAG, "onFailure: ${e.message.toString()}")
        }
    }

    fun fetchDetailGithubUser(username: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                getDetailGithubUser(username).collect { userDetail ->
                    _detailGithubUser.value = userDetail
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    suspend fun getGithubFollowers(username: String): Flow<List<UserItem>> = flow {
        try {
            val response = apiService.getFollowers(username)
            val domainFollowers = response.map { it.toDomainModel() }
            emit(domainFollowers)
        } catch (e: Exception) {
            Log.e(TAG, "onFailure: ${e.message.toString()}")
        }
    }

    fun fetchGithubFollowers(username: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                getGithubFollowers(username).collect { followersList ->
                    _followers.value = followersList
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    suspend fun getGithubFollowing(username: String): Flow<List<UserItem>> = flow {
        try {
            val response = apiService.getFollowing(username)
            val domainFollowing = response.map { it.toDomainModel() }
            emit(domainFollowing)
        } catch (e: Exception) {
            Log.e(TAG, "onFailure: ${e.message.toString()}")
        }
    }

    fun fetchGithubFollowing(username: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                getGithubFollowing(username).collect { followingList ->
                    _followings.value = followingList
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }
}