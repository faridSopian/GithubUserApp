package com.bangkitacademy.githubuserapp.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.bangkitacademy.githubuserapp.R
import com.bangkitacademy.githubuserapp.core.domain.model.Favorite
import com.bangkitacademy.githubuserapp.databinding.ActivityDetailBinding
import com.bangkitacademy.githubuserapp.adapter.SectionPagerAdapter
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()
    private var isFavorite: Boolean = false

    companion object{
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.followers,
            R.string.following
        )
        const val EXTRA_USERNAME = "username"
        const val EXTRA_AVATAR = "extra_avatar"
        const val EXTRA_URL = "extra_url"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val getUsername = intent.getStringExtra(EXTRA_USERNAME) ?: ""
        val avatar = intent.getStringExtra(EXTRA_AVATAR) ?: ""
        val urlHtml = intent.getStringExtra(EXTRA_URL) ?: ""
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        detailViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        val sectionsPagerAdapter = SectionPagerAdapter(this, getUsername)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tab_layout)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        detailViewModel.fetchDetailGithubUser(getUsername)

        detailViewModel.detailGithubUser.observe(this) {
            if (it != null) {
                Glide.with(this@DetailActivity)
                    .load(it.avatarUrl)
                    .centerCrop()
                    .into(binding.imgUserDetail)
                binding.githubName.text = it.githubName
                binding.githubUsername.text = it.githubUsername
                binding.githubFollowers.text = "${it.githubFollowers} Followers"
                binding.githubFollowing.text = "${it.githubFollowing} Following"
            }
        }

        detailViewModel.getFavByUsername(getUsername).observe(this) {
            isFavorite = it.isNotEmpty()
            val favoriteUser = Favorite(getUsername, avatar, urlHtml)
            if (it.isEmpty()) {
                binding.fabFav.setImageDrawable(
                    ContextCompat.getDrawable(
                        binding.fabFav.context,
                        R.drawable.baseline_favorite_border_24
                    )
                )
            } else {
                binding.fabFav.setImageDrawable(
                    ContextCompat.getDrawable(
                        binding.fabFav.context,
                        R.drawable.baseline_favorite_24
                    )
                )
            }

            binding.fabFav.setOnClickListener {
                if (isFavorite) {
                    detailViewModel.deleteFav(favoriteUser)
                    Toast.makeText(this, R.string.favorite_remove, Toast.LENGTH_SHORT).show()
                } else {
                    detailViewModel.insertFav(favoriteUser)
                    Toast.makeText(this, R.string.favorite_add, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}