package com.example.nestedrvdemo.model

import com.example.nestedrvdemo.ui.main.data.item.NestedItem
import com.example.nestedrvdemo.ui.main.data.item.OuterItemType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

object FakeRepo {

    fun outerItems(): Flow<List<OuterItemType>> = flow {
        val list = buildList {
            add(OuterItemType.NestedListItem(id = "NestedListItem", items = nestedItems))
            addAll(outerItems)
        }
        emit(list)
    }.flowOn(Dispatchers.IO)

    private val nestedItems = (0..15).map { n ->
        NestedItem(id = "id_$n", isSelected = n == 0)
    }

    private val outerItems = (1..15).map { n ->
        OuterItemType.OuterItem(id = "id_$n")
    }
}