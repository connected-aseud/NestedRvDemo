package com.example.nestedrvdemo.ui.main.data.item

sealed interface OuterItemType {
    val id: String

    data class NestedListItem(
        override val id: String,
        val items: List<NestedItem>
    ) : OuterItemType

    data class OuterItem(
        override val id: String
    ): OuterItemType
}
