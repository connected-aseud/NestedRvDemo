package com.example.nestedrvdemo.ui.main

import androidx.lifecycle.ViewModel
import com.example.nestedrvdemo.ui.main.data.item.NestedItem
import com.example.nestedrvdemo.ui.main.data.item.OuterItemType
import com.example.nestedrvdemo.model.FakeRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private val _state = MutableStateFlow<Payload?>(null)
    val state = _state.filterNotNull()

    init {
        fetch()
    }

    fun onNestedItemClick(item: NestedItem) {
        if (item.isSelected) return

        updateSelection(clickedItem = item)
    }

    private fun fetch() = viewModelScope.launch {
        FakeRepo.outerItems().collect { items ->
            _state.tryEmit(Payload(items))
        }
    }

    private fun updateSelection(clickedItem: NestedItem) = _state.value?.let { payload ->
        val updatedNestedItems = payload.nestedItems.map { currentItem ->
            val isItemClicked = clickedItem.id == currentItem.id
            val shouldSelect = !currentItem.isSelected && isItemClicked
            val shouldUnselect = currentItem.isSelected && !isItemClicked
            if (shouldSelect || shouldUnselect) {
                currentItem.copy(isSelected = !currentItem.isSelected)
            } else {
                currentItem
            }
        }

        val updatedOuterItems = payload.items.map { currentOuterItem ->
            when (currentOuterItem) {
                is OuterItemType.NestedListItem -> currentOuterItem.copy(items = updatedNestedItems)
                is OuterItemType.OuterItem -> currentOuterItem
            }
        }

        _state.tryEmit(Payload(updatedOuterItems))
    }

    class Payload(
        val items: List<OuterItemType>
    ) {
        val nestedItems
            get() = items
                .find { it is OuterItemType.NestedListItem }
                .let { it as OuterItemType.NestedListItem }
                .items

    }
}