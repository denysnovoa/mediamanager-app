package com.dnovoa.deluge.repository.data.storage.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object DelugeSessionCache {

    private var userSession: DelugeSessionDto? = null

    fun getSession(): Flow<DelugeSessionDto?> =
        flow { emit(userSession) }

    fun saveSession(userSession: DelugeSessionDto?) {
        this.userSession = userSession
    }
}