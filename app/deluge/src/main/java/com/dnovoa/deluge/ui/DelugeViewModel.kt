package com.dnovoa.deluge.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dnovoa.deluge.repository.DelugeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DelugeViewModel(private val repository: DelugeRepository) : ViewModel() {

    private val _loginIsVisible = MutableStateFlow(false)
    val loginIsVisible: MutableStateFlow<Boolean> get() = _loginIsVisible

    private val _showMessage = MutableStateFlow("")
    val showMessage: MutableStateFlow<String> get() = _showMessage

    init {
        viewModelScope.launch {
            repository.getUserSession().collect { delugeUserSession ->
                _loginIsVisible.value = delugeUserSession == null
            }
        }
    }

    fun login() {
        viewModelScope.launch {
            repository.login()
                .collect { delugeUserSession ->
                    _loginIsVisible.value = delugeUserSession == null
                }
        }
    }

    fun updatedSpeedDownload(speed: Int) {
        viewModelScope.launch {
            repository.updatedTorrentSpeed(speed)
                .collect { _showMessage.value = it.toString() }
        }
    }
}