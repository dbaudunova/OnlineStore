package com.example.techtaskdeveem.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.techtaskdeveem.data.remote.model.Product
import com.example.techtaskdeveem.databinding.ItemProductBinding
import com.example.techtaskdeveem.utils.loadImage

class ProductAdapter(
    val onClick: (Product) -> Unit
) : ListAdapter<Product, ProductAdapter.CategoryViewHolder>(ProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class CategoryViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(product: Product) {
            binding.ivImage.loadImage(product.image)
            binding.tvTitle.text = product.title
            binding.tvPrice.text = product.price.toString() + " $"
            itemView.setOnClickListener {
                onClick(product)
            }
        }
    }

    private class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
}