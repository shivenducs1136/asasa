package com.example.zomatoapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zomatoapp.Repository.Repository


// View Model factory of city.
    class CityViewModelFactory (private val repository: Repository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CityViewModel(repository) as T
        }
    }