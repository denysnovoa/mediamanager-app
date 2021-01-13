package com.dnovoa.deluge.di

import com.dnovoa.deluge.repository.DelugeRepository
import com.dnovoa.deluge.repository.data.api.DelugeApiService
import com.dnovoa.deluge.repository.data.api.okHttpKtor

object DelugeServiceLocator {

    fun initDelugeRepository(): DelugeRepository = DelugeRepository(initDelugeApiService())
    private fun initDelugeApiService(): DelugeApiService = DelugeApiService(httpClient = okHttpKtor)
}