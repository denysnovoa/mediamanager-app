package com.dnovoa.deluge.repository.data.api.model

import kotlinx.serialization.Serializable

@Serializable
data class DelugeStatsDto(
    val max_download: Double,
    val max_upload: Double,
    val max_num_connections: Int,
    val num_connections: Int,
    val upload_rate: Double,
    val download_rate: Double,
    val download_protocol_rate: Double,
    val upload_protocol_rate: Double,
    val dht_nodesval: Int,
    val has_incoming_connections: Int,
    val free_space: Long,
    val external_ip: String,
)