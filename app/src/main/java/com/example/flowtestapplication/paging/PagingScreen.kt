package com.example.flowtestapplication.paging

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun PagingScreen(
    modifier: Modifier = Modifier,
    viewModel: PagingViewModel = viewModel(),
) {
    val pagingItems = viewModel.pagedData.collectAsLazyPagingItems()
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp),
    ) {
        items(
            pagingItems.itemCount,
            key = { pagingItems.peek(it)?.text ?: it }
        ) { index ->
            val item = pagingItems[index]
            if (item != null) {
                Text(
                    item.text,
                    Modifier
                        .padding(4.dp)
                        .animateItem()
                )
            } else {
                Box(
                    Modifier
                        .animateItem()
                        .background(Color.LightGray, RoundedCornerShape(4.dp))
                        .height(40.dp)
                        .fillParentMaxWidth()
                        .padding(8.dp),
                    contentAlignment = Alignment.CenterStart,
                ) {
                    Text("Loading...")
                }
            }
        }
    }
}