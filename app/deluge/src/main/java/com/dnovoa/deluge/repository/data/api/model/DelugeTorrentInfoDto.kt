package com.dnovoa.deluge.repository.data.api.model

import kotlinx.serialization.Serializable

@Serializable
data class DelugeTorrentInfoDto(
    val time_added: Int,
    val eta: Int,
    val label: String,
    val total_done: Long,
    val name: String,
    val progress: Double,
    val is_auto_managed: Boolean,
    val state: String,
    val ratio: Double,
    val download_payload_rate: Int,
    val tracker_host: String,
    val total_remaining: Long,
    val distributed_copies: Double,
    val total_peers: Int,
    val last_seen_complete: Int,
    val max_upload_speed: Int,
    val total_uploaded: Int,
    val seeds_peers_ratio: Double,
    val total_wanted: Long,
    val total_seeds: Int,
    val num_peers: Int,
    val queue: Int,
    val download_location: String,
    val upload_payload_rate: Int,
    val max_download_speed: Int,
    val num_seeds: Int,
    val completed_time: Int,
    val time_since_transfer: Int,
)