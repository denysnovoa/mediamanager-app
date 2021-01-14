package com.dnovoa.deluge.repository.data.api.model

import kotlinx.serialization.Serializable

@Serializable
sealed class DelugeRequestDto {
    abstract val method: String
    abstract val id: String

    @Serializable
    data class Login(
        override val method: String = "auth.login",
        override val id: String,
        val params: List<String>
    ) : DelugeRequestDto()

    @Serializable
    data class SetConfig(
        override val method: String = "core.set_config",
        override val id: String,
        val params: List<Map<String, String>>
    ) : DelugeRequestDto()

    @Serializable
    data class Ui(
        override val method: String = "web.update_ui",
        override val id: String,
        val params: Array<List<String>>
    ) : DelugeRequestDto()
}

//                                    "max_upload_speed",