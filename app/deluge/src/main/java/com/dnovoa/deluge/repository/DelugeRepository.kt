package com.dnovoa.deluge.repository

import com.dnovoa.deluge.repository.data.api.DelugeApiService

class DelugeRepository(private val delugeApiService: DelugeApiService) {

    suspend fun login() = delugeApiService.login()
}