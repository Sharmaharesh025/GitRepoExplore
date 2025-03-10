package com.example.gitrepoexplore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gitrepoexplore.databinding.ItemRepositoryBinding

class RepositoryAdapter(private var repositories: MutableList<Repository>) :
    RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {

    inner class RepositoryViewHolder(private val binding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(repository: Repository) {
            binding.repoName.text = repository.name
            binding.repoDescription.text = repository.description ?: "No description"
            binding.repoLanguage.text = repository.language ?: "Unknown"
            binding.repoStars.text = "⭐ ${repository.stargazers_count}"
            binding.repoForks.text = "🍴 ${repository.forks_count}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val binding = ItemRepositoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(repositories[position])
    }

    override fun getItemCount(): Int = repositories.size

    fun clearData() {
        repositories.clear()
        notifyDataSetChanged()
    }

    fun updateData(newRepositories: List<Repository>) {
        val oldSize = repositories.size
        repositories.addAll(newRepositories)
        notifyItemRangeInserted(oldSize, newRepositories.size)
    }
}
