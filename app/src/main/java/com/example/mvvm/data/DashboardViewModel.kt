package com.example.mvvm.data

import androidx.lifecycle.ViewModel
import com.example.mvvm.api.Locations
import com.example.mvvm.api.retrofit

class DashboardViewModel : ViewModel() {

suspend fun load(page: Int): List<Locations> {
    return retrofit.loadListLocations(page).results
}
}
