package com.example.mvvm.data

import androidx.lifecycle.ViewModel
import com.example.mvvm.api.Episodes
import com.example.mvvm.api.retrofit

class NotificationsViewModel : ViewModel() {
    suspend fun load(number: Int): Episodes {
        return retrofit.loadEpisodes(number)
    }
}