package com.kurly.task.searchgithub.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kurly.task.searchgithub.R
import com.kurly.task.searchgithub.model.RepositoryModel
import com.kurly.task.searchgithub.ui.main.RepositoryAdapter

object BindingAdapter {

    @BindingAdapter("repoData")
    @JvmStatic
    fun bindData(recyclerView: RecyclerView, repositories: List<RepositoryModel>?) {

        val adapter = recyclerView.adapter as RepositoryAdapter
        adapter.submitList(repositories)
    }

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(imageView: ImageView, url: String) {

        Glide.with(imageView.context).load(url)
            .error(R.drawable.ic_launcher_background)
            .into(imageView)
    }
}