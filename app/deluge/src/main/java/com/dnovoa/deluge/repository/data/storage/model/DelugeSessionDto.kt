package com.dnovoa.deluge.repository.data.storage.model

data class DelugeSessionDto(
    val useId: DelugeUserId,
    val userSession: DelugeUserSession
)

inline class DelugeUserId(val id: Int)
inline class DelugeUserSession(val session: String)
