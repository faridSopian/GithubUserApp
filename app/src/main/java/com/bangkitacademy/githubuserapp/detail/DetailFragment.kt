package com.bangkitacademy.githubuserapp.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkitacademy.githubuserapp.core.data.source.remote.response.ItemsItem
import com.bangkitacademy.githubuserapp.databinding.FragmentDetailBinding
import com.bangkitacademy.githubuserapp.adapter.GithubConnectionAdapter
import com.bangkitacademy.githubuserapp.core.domain.model.UserItem
import org.koin.android.viewmodel.ext.android.viewModel


class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()

    private var position = 0
    private var username: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View ,savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            position = it.getInt(ARG_POSITION)
            username = it.getString(ARG_USERNAME) ?: "John"
        }

        detailViewModel.fetchGithubFollowers(username)
        detailViewModel.fetchGithubFollowing(username)

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvConnections.layoutManager = layoutManager

        detailViewModel.isLoading.observe(viewLifecycleOwner){
            showLoading(it)
        }

        if (position == 1){
            detailViewModel.followers.observe(viewLifecycleOwner){
                setGithubConnections(it)
            }
        }else{
            detailViewModel.followings.observe(viewLifecycleOwner){
                setGithubConnections(it)
            }
        }


    }

    private fun setGithubConnections(githubConnect: List<UserItem>){
        val adapter = GithubConnectionAdapter()
        adapter.submitList(githubConnect)
        binding.rvConnections.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    companion object {
        const val ARG_USERNAME = "John"
        const val ARG_POSITION = "0"
    }
}