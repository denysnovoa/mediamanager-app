package com.dnovoa.deluge.repository.data.api.model

import kotlinx.serialization.Serializable

@Serializable
sealed class DelugeRequestDto {
    abstract val method: String
    abstract val id: String

    @Serializable
    data class Login(
        override val method: String,
        override val id: String,
        val params: List<String>
    ) : DelugeRequestDto()

    @Serializable
    data class SetConfig(
        override val method: String,
        override val id: String,
        val params: List<Map<String, String>>
    ) : DelugeRequestDto()

    @Serializable
    data class Ui(
        override val method: String,
        override val id: String,
        val params: ArrayList<List<String>>
    ) : DelugeRequestDto()
}

//                                    "max_upload_speed",