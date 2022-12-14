package com.example.nestedrvdemo.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nestedrvdemo.ui.main.data.item.NestedItem
import com.example.nestedrvdemo.ui.main.data.item.OuterItemType
import com.example.nestedrvdemo.R
import com.example.nestedrvdemo.databinding.ItemOuterLayoutBinding
import com.example.nestedrvdemo.databinding.ItemOuterNestedRvLayoutBinding
import com.example.nestedrvdemo.ui.main.viewholder.OuterItemViewHolder
import com.example.nestedrvdemo.ui.main.viewholder.OuterNestedListViewHolder

class OuterAdapter(
    private val onNestedItemClick: (NestedItem) -> Unit
) : ListAdapter<OuterItemType, RecyclerView.ViewHolder>(
    object : DiffUtil.ItemCallback<OuterItemType>() {
        override fun areItemsTheSame(oldItem: OuterItemType, newItem: OuterItemType): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: OuterItemType, newItem: OuterItemType): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
            return when (viewType) {
                R.layout.item_outer_nested_rv_layout -> OuterNestedListViewHolder(
                    binding = ItemOuterNestedRvLayoutBinding.inflate(layoutInflater, parent, false),
                    onClick = onNestedItemClick
                )
                R.layout.item_outer_layout -> OuterItemViewHolder(
                    binding = ItemOuterLayoutBinding.inflate(layoutInflater, parent, false)
                )
                else -> null!!
            }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = currentList[position]) {
            is OuterItemType.NestedListItem -> (holder as? OuterNestedListViewHolder)?.bind(item)
            is OuterItemType.OuterItem -> {}
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (currentList[position]) {
            is OuterItemType.NestedListItem -> R.layout.item_outer_nested_rv_layout
            is OuterItemType.OuterItem -> R.layout.item_outer_layout
        }
    }

}