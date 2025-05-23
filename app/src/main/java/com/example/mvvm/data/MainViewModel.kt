package com.example.mvvm.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.mvvm.api.MovieCharacters
import kotlinx.coroutines.flow.Flow

class MainViewModel private constructor(private val repository: PhotoRepository) : ViewModel() {
    constructor() : this(PhotoRepository())

    val pagedPhoto: Flow<PagingData<MovieCharacters>> = Pager(
        config = PagingConfig(pageSize = 15),
        pagingSourceFactory = { PhotoPagingSource() }).flow.cachedIn(viewModelScope)
}