package com.bangkitacademy.githubuserapp.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkitacademy.githubuserapp.R
import com.bangkitacademy.githubuserapp.core.domain.model.Favorite
import com.bangkitacademy.githubuserapp.databinding.ActivityFavoriteBinding
import com.bangkitacademy.githubuserapp.detail.DetailActivity
import com.bangkitacademy.githubuserapp.core.ui.FavoriteAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.title = getString(R.string.favorite)

        favoriteViewModel.getAllFavorite().observe(this){
            setFavoriteUser(it)
        }

        favoriteViewModel.isLoading.observe(this){
            showLoading(it)
        }
    }

    private fun setFavoriteUser(userEntity: List<Favorite>){
        val userItems = arrayListOf<Favorite>()
        userEntity.map {
            val item = Favorite(
                username = it.username,
                avatarUrl = it.avatarUrl,
                githubName = it.githubName
            )
            userItems.add(item)
        }
        val adapter = FavoriteAdapter(userItems)
        binding.rvFavoriteUser.layoutManager = LinearLayoutManager(this)
        binding.rvFavoriteUser.setHasFixedSize(true)
        binding.rvFavoriteUser.adapter = adapter

        adapter.setOnItemClickCallback(object : FavoriteAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Favorite) {
                val intent = Intent(this@FavoriteActivity, DetailActivity::class.java)
                    .putExtra(DetailActivity.EXTRA_USERNAME, data.username)
                    .putExtra(DetailActivity.EXTRA_AVATAR, data.avatarUrl)

                startActivity(intent)
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}