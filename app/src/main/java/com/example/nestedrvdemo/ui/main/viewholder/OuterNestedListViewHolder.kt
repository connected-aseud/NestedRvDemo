package com.example.nestedrvdemo.ui.main.viewholder

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nestedrvdemo.databinding.ItemNestedRvLayoutBinding
import com.example.nestedrvdemo.ui.main.adapter.NestedAdapter
import com.example.nestedrvdemo.ui.main.data.item.NestedItem
import com.example.nestedrvdemo.ui.main.data.item.OuterItemType
import com.example.nestedrvdemo.utils.TAG

class OuterNestedListViewHolder(
    binding: ItemNestedRvLayoutBinding,
    private val onClick: (NestedItem) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        Log.d(TAG, "init: OuterNestedListViewHolder ${this.hashCode()}")
    }

    private val adapter by lazy {
        NestedAdapter(onClick)
    }

    private val layoutManager by lazy {
        LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
    }

    init {
        binding.nestedRv.adapter = adapter
        binding.nestedRv.layoutManager = layoutManager
    }

    fun bind(item: OuterItemType.NestedListItem) {
        adapter.submitList(item.items)
    }
}