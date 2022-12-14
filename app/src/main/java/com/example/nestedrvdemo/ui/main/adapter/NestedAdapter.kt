package com.example.nestedrvdemo.ui.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.nestedrvdemo.ui.main.data.item.NestedItem
import com.example.nestedrvdemo.databinding.ItemNestedItemLayoutBinding
import com.example.nestedrvdemo.ui.main.viewholder.NestedViewHolder
import com.example.nestedrvdemo.utils.TAG

class NestedAdapter(
    private val onClick: (NestedItem) -> Unit
) : ListAdapter<NestedItem, NestedViewHolder>(
    object : DiffUtil.ItemCallback<NestedItem>() {
        override fun areItemsTheSame(oldItem: NestedItem, newItem: NestedItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NestedItem, newItem: NestedItem): Boolean {
            return oldItem == newItem
        }
    }
) {

    init {
        Log.d(TAG, "init: NestedAdapter ${this.hashCode()}")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NestedViewHolder {
        return NestedViewHolder(
            binding = ItemNestedItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClick = onClick
        )
    }

    override fun onBindViewHolder(holder: NestedViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}
