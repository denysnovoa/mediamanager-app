package com.dnovoa.deluge.repository.data.api.model

import kotlinx.serialization.Serializable

@Serializable
data class DelugeLoginRequestDto(
    val method: String,
    val params: List<String>,
    val id: String
)