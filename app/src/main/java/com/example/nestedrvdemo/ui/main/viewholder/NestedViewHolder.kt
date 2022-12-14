package com.example.nestedrvdemo.ui.main.viewholder

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.nestedrvdemo.R
import com.example.nestedrvdemo.databinding.ItemNestedItemLayoutBinding
import com.example.nestedrvdemo.ui.main.data.item.NestedItem

class NestedViewHolder(
    private val binding: ItemNestedItemLayoutBinding,
    private val onClick: (NestedItem) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: NestedItem) {
        val colorRes = if (item.isSelected) R.color.gray_400 else R.color.black
        val color = ContextCompat.getColor(itemView.context, colorRes)
        binding.root.setBackgroundColor(color)
        binding.root.text = item.id

        binding.root.setOnClickListener { onClick(item) }
    }
}