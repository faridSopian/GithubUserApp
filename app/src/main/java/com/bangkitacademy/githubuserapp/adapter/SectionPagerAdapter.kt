package com.bangkitacademy.githubuserapp.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bangkitacademy.githubuserapp.detail.DetailFragment

class SectionPagerAdapter(activity: AppCompatActivity, val username: String): FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = DetailFragment()
        fragment.arguments = Bundle().apply {
            putInt(DetailFragment.ARG_POSITION, position + 1)
            putString(DetailFragment.ARG_USERNAME, username)
        }
        return fragment
    }

}