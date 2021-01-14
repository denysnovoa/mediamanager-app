package com.dnovoa.deluge.repository.data.api.model

import kotlinx.serialization.Serializable

@Serializable
class DelugeFiltersDto(
    val state: List<Pair<String, String>>,
    val tracker_host: List<Pair<String, String>>,
    val owner: List<Pair<String, String>>,
    val label: List<Pair<String, String>>,
)