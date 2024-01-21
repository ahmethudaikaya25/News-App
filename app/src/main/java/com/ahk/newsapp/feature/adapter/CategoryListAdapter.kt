package com.ahk.newsapp.feature.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.ahk.newsapp.databinding.ArticleCategoryButtonItemBinding
import com.ahk.newsapp.feature.home_page.model.CategoryButtonData
import com.ahk.newsapp.feature.util.ItemClickListener

class CategoryListAdapter(
    private val itemClickListener: ItemClickListener<CategoryButtonData>,
) : Adapter<CategoryListAdapter.ViewHolder>() {
    var categoryList: List<CategoryButtonData> = emptyList()

    private class DiffCallback(
        private val oldList: List<CategoryButtonData>,
        private val newList: List<CategoryButtonData>,
    ) :
        DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldList[oldItemPosition]
            val newItem = newList[newItemPosition]
            return areItemsSame(oldItem, newItem)
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldList[oldItemPosition]
            val newItem = newList[newItemPosition]
            return areContentsSame(oldItem, newItem)
        }

        private fun areItemsSame(
            oldItem: CategoryButtonData,
            newItem: CategoryButtonData,
        ): Boolean {
            // Implement your logic to determine if two items represent the same object
            return (oldItem.name == newItem.name)
        }

        private fun areContentsSame(
            oldItem: CategoryButtonData,
            newItem: CategoryButtonData,
        ): Boolean {
            // Implement your logic to determine if the item content has changed
            return (oldItem == newItem)
        }
    }

    inner class ViewHolder(private val binding: ArticleCategoryButtonItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CategoryButtonData) {
            binding.categoryButtonData = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ArticleCategoryButtonItemBinding.inflate(inflater)
        binding.onItemClick = itemClickListener
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun submitList(list: List<CategoryButtonData>) {
        val diffCallback = DiffCallback(
            categoryList,
            list,
        )
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(this)
        categoryList = list.toList()
    }
}
