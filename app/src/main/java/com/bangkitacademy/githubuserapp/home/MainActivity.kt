package com.bangkitacademy.githubuserapp.home

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkitacademy.githubuserapp.R
import com.bangkitacademy.githubuserapp.adapter.GithubUserAdapter
import com.bangkitacademy.githubuserapp.databinding.ActivityMainBinding
import com.bangkitacademy.githubuserapp.favorite.FavoriteActivity
import com.bangkitacademy.githubuserapp.core.domain.model.UserItem
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val layoutManager = LinearLayoutManager(this)
        binding.rvView.layoutManager = layoutManager

        mainViewModel.getTheme().observe(this) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        with(binding){
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    searchBar.setText(searchView.text)
                    searchView.hide()
                    mainViewModel.fetchGithubUsers(searchView.text.toString())
                    mainViewModel.listGithub.observe(this@MainActivity){
                        if (it.isNullOrEmpty()){
                            checkAvailableUser(true)
                        }else{
                            checkAvailableUser(false)
                        }
                    }
                    false
                }
            searchBar.inflateMenu(R.menu.menu_main)
            searchBar.setOnMenuItemClickListener{
                when(it.itemId){
                    R.id.favuserMenu -> {
                        val intent = Intent(this@MainActivity, FavoriteActivity::class.java)
                        startActivity(intent)
                        true
                    }

                    R.id.themeMenu -> {
                        val uri = Uri.parse("githubuserapp://theme")
                        startActivity(Intent(Intent.ACTION_VIEW, uri))
                        true
                    }
                    else -> false
                }
            }
        }

        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        mainViewModel.listGithub.observe(this) {
            if (it != null) {
                setGithubUsers(it)
            }
        }

    }

    private fun checkAvailableUser(isDataNotFound: Boolean) {
        binding.apply {
            if (isDataNotFound) {
                rvView.visibility = View.GONE
                tvUserNotFound.visibility = View.VISIBLE
            } else {
                rvView.visibility = View.VISIBLE
                tvUserNotFound.visibility = View.GONE
            }
        }
    }

    private fun setGithubUsers(githubUsers: List<UserItem>) {
        val adapter = GithubUserAdapter()
        adapter.submitList(githubUsers)
        binding.rvView.adapter = adapter
    }

    fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}