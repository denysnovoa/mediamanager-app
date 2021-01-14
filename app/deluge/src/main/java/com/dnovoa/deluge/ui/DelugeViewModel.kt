package com.dnovoa.deluge.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dnovoa.deluge.repository.DelugeRepository
import com.dnovoa.deluge.repository.data.api.model.DelugeStatsDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DelugeViewModel(private val repository: DelugeRepository) : ViewModel() {

    private val _loginIsVisible = MutableStateFlow(false)
    val loginIsVisible: MutableStateFlow<Boolean> get() = _loginIsVisible

    private val _showMessage = MutableStateFlow("")
    val showMessage: MutableStateFlow<String> get() = _showMessage

    init {
        viewModelScope.launch {
            repository.getUserSession()
                .catch { _showMessage.value = it.stackTraceToString() }
                .collect { delugeUserSession ->
                    _loginIsVisible.value = delugeUserSession == null
                }
        }
    }

    fun login() {
        viewModelScope.launch {
            repository.login()
                .catch { _showMessage.value = it.stackTraceToString() }
                .collect { delugeUserSession ->
                    _loginIsVisible.value = delugeUserSession == null
                }
        }
    }

    fun updatedSpeedDownload(speed: Int) {
        viewModelScope.launch {
            repository.updatedTorrentSpeed(speed)
                .catch { _showMessage.value = it.stackTraceToString() }
                .collect {
                    _showMessage.value = it.toString()
                }
        }
    }

    fun refreshUI() {
        viewModelScope.launch {
            repository.getUiInfo()
                .catch { _showMessage.value = it.stackTraceToString() }
                .collect { delugeResponseDto ->
                    val downloadRate: Double = delugeResponseDto.stats.download_rate

                    _showMessage.value = when {
                        downloadRate <= 1000 -> { (downloadRate / 1000).toString() + "Kib/s" }
                        downloadRate <= 10000 -> { (downloadRate / 10000).toString() + "Mb/s" }
                        else -> { (downloadRate / 100000).toString() + "Gb/s" }
                    }
                }
        }
    }
}