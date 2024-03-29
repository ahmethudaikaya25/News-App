package com.ahk.newsapp.feature.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ahk.newsapp.databinding.ArticleListItemBinding
import com.ahk.newsapp.feature.home_page.model.ArticleEntity
import com.ahk.newsapp.feature.util.ItemClickListener

class ArticleListAdapter(
    private val itemClickListener: ItemClickListener<ArticleEntity>,
    private val onBookmarkClickListener: ItemClickListener<ArticleEntity>,
) :
    PagingDataAdapter<ArticleEntity, ArticleListAdapter.ViewHolder>(diffCallback) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class ViewHolder(private val binding: ArticleListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ArticleEntity) {
            binding.articleListItemEntity = item
            binding.onBookmarkClick = onBookmarkClickListener
            binding.onBookmarkClick = onBookmarkClickListener
            binding.executePendingBindings()
        }
    }

    companion object {

        private val diffCallback = object : DiffUtil.ItemCallback<ArticleEntity>() {
            override fun areItemsTheSame(oldItem: ArticleEntity, newItem: ArticleEntity): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(
                oldItem: ArticleEntity,
                newItem: ArticleEntity,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ArticleListItemBinding.inflate(inflater)
        binding.onItemClick = itemClickListener
        return ViewHolder(binding)
    }
}
