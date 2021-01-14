package com.dnovoa.deluge.repository

import com.dnovoa.deluge.repository.data.api.DelugeApiService
import com.dnovoa.deluge.repository.data.storage.model.DelugeSessionCache
import kotlinx.coroutines.flow.map

class DelugeRepository(
    private val delugeApiService: DelugeApiService,
    private val delugeSessionCache: DelugeSessionCache
) {

    fun login() = delugeApiService.login()
        .map {
            delugeSessionCache.saveSession(it)
            it
        }

    fun getUserSession() = delugeSessionCache.getSession()
}