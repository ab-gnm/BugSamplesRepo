package com.example.flowtestapplication.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay
import java.io.IOException

data class Item(val text: String)

class TestPagingSource : PagingSource<Int, Item>() {
    override fun getRefreshKey(state: PagingState<Int, Item>) = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        val key = params.key

        // Pretend network delay
        delay(1000)

        return when (key) {
            // Start with an empty page of placeholders
            null -> LoadResult.Page(
                data = emptyList(),
                nextKey = 1,
                prevKey = null,
                itemsBefore = 0,
                itemsAfter = ITEM_COUNT,
            )

            // Third page errors out while fetching
            3 -> LoadResult.Error(IOException("Error loading data"))

            // Return page with 10 items
            else -> LoadResult.Page(
                data = List(ITEM_COUNT) { index ->
                    Item("Item $key - ${index + 1}")
                },
                prevKey = if (key == 1) null else key - 1,
                nextKey = key + 1,
                itemsBefore = if (params.key == 1) 0 else ITEM_COUNT,
                itemsAfter = ITEM_COUNT,
            )
        }
    }

    companion object {
        private const val ITEM_COUNT = 8
    }
}