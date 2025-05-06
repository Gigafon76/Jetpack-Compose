package com.example.mvvm.data

import com.example.mvvm.api.MovieCharacters
import com.example.mvvm.api.retrofit

class PhotoRepository {
    suspend fun getPhoto(page: Int): List<MovieCharacters> {
        return retrofit.loadList(page).results
    }
}