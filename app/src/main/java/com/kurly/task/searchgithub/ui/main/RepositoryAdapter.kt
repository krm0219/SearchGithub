package com.kurly.task.searchgithub.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kurly.task.searchgithub.R
import com.kurly.task.searchgithub.databinding.ItemGithubBinding
import com.kurly.task.searchgithub.model.RepositoryModel

class RepositoryAdapter() : ListAdapter<RepositoryModel, RepositoryAdapter.ViewHolder>(RepoDiffUtil) {

    var repositories: List<RepositoryModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemGithubBinding>(layoutInflater, viewType, parent, false)
        return ViewHolder(binding);
    }

    override fun getItemViewType(position: Int): Int {

        return R.layout.item_github
    }

    override fun getItemCount(): Int {

        return repositories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val repository = repositories[position]
        holder.bind(repository)
    }

    inner class ViewHolder(private val binding: ItemGithubBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(repository: RepositoryModel) {

            binding.repository = repository
            binding.executePendingBindings()
        }
    }

    companion object RepoDiffUtil : DiffUtil.ItemCallback<RepositoryModel>() {
        override fun areItemsTheSame(oldItem: RepositoryModel, newItem: RepositoryModel): Boolean {

            // 각 아이템들의 고유한 값을 비
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: RepositoryModel, newItem: RepositoryModel): Boolean {

            return oldItem == newItem
        }
    }

    fun setData(repositories: List<RepositoryModel>) {

        this.repositories = repositories
        notifyDataSetChanged()
    }
}