package com.bangkitacademy.githubuserapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bangkitacademy.githubuserapp.core.data.source.remote.response.ItemsItem
import com.bangkitacademy.githubuserapp.core.databinding.ItemReviewBinding
import com.bangkitacademy.githubuserapp.core.domain.model.UserItem
import com.bangkitacademy.githubuserapp.detail.DetailActivity
import com.bumptech.glide.Glide

class GithubConnectionAdapter: ListAdapter<UserItem, GithubConnectionAdapter.MyViewHolder>(
    DIFF_CALLBACK
) {

    class MyViewHolder(private val binding: ItemReviewBinding):  RecyclerView.ViewHolder(binding.root) {
        fun bind(userItem: UserItem){
            Glide.with(itemView.context).load(userItem.avatarUrl).into(binding.imgUser)
            binding.tvUsername.text = userItem.githubUsername
            binding.tvGithubname.text = userItem.githubName

            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, DetailActivity::class.java)
                intent.putExtra(EXTRA_USERNAME, userItem.githubUsername)
                intent.putExtra(EXTRA_AVATAR, userItem.avatarUrl)
                intent.putExtra(EXTRA_URL, userItem.githubName)
                binding.root.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserItem>(){
            override fun areItemsTheSame(
                oldItem: UserItem,
                newItem: UserItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: UserItem,
                newItem: UserItem
            ): Boolean {
                return oldItem == newItem
            }
        }

        const val EXTRA_USERNAME = "username"
        const val EXTRA_AVATAR = "extra_avatar"
        const val EXTRA_URL = "extra_url"
    }

}