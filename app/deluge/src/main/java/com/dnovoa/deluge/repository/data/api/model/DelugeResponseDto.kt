package com.dnovoa.deluge.repository.data.api.model

import kotlinx.serialization.Serializable

@Serializable
data class DelugeResponseDto(
    val connected: Boolean,
    val filters: DelugeFiltersDto,
    val torrentInfoDto: DelugeTorrentInfoDto,
    val stats: DelugeStatsDto,
)
