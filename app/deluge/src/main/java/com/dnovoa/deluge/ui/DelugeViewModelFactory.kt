package com.dnovoa.deluge.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dnovoa.deluge.repository.DelugeRepository

class DelugeViewModelFactory(private val repository: DelugeRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DelugeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DelugeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}