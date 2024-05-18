package com.bangkitacademy.githubuserapp.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bangkitacademy.githubuserapp.core.domain.model.Favorite
import com.bangkitacademy.githubuserapp.core.databinding.ItemReviewBinding
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView


class FavoriteAdapter(private val listUserFavorite: List<Favorite>) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: Favorite)
    }

    class ViewHolder(binding: ItemReviewBinding): RecyclerView.ViewHolder(binding.root) {
        val tvUser: TextView = binding.tvUsername
        val tvUrl: TextView = binding.tvGithubname
        val ivPhoto: CircleImageView = binding.imgUser
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listUserFavorite.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvUser.text = listUserFavorite[position].username
        holder.tvUrl.text = listUserFavorite[position].githubName
        Glide.with(holder.itemView.context)
            .load(listUserFavorite[position].avatarUrl)
            .into(holder.ivPhoto)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listUserFavorite[holder.adapterPosition])
        }
    }

}