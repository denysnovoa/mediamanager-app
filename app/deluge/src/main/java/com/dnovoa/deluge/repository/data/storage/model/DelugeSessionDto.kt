package com.dnovoa.deluge.repository.data.storage.model

data class DelugeSessionDto(
    val useId: DelugeUserId,
    val userSession: DelugeUserSession
)

data class DelugeUserId(val id: Int)
data class DelugeUserSession(val session: String)
