package com.dnovoa.deluge.ui

import androidx.lifecycle.ViewModel
import com.dnovoa.deluge.repository.DelugeRepository

class DelugeViewModel(private val repository: DelugeRepository) : ViewModel() {

    fun login() = repository.login()
}