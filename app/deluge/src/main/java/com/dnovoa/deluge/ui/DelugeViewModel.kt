package com.dnovoa.deluge.ui

import androidx.lifecycle.ViewModel
import com.dnovoa.deluge.di.DelugeServiceLocator

class DelugeViewModel : ViewModel() {
    private val delugeRepository = DelugeServiceLocator.initDelugeRepository()

    suspend fun login() = delugeRepository.login()
}