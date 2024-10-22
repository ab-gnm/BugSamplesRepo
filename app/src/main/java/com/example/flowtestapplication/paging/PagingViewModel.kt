package com.example.flowtestapplication.paging

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PagingViewModel @Inject constructor() : ViewModel() {
    val pagedData = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = true,
        ),
        pagingSourceFactory = {
            TestPagingSource()
        },
    ).flow
}