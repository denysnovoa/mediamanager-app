package com.dnovoa.deluge.repository.data.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.logging.SIMPLE
import io.ktor.client.request.header
import io.ktor.util.KtorExperimentalAPI

@KtorExperimentalAPI
val okHttpKtor = HttpClient(CIO) {
    install(JsonFeature) {
        serializer = KotlinxSerializer()
    }

    install(Logging) {
        logger = Logger.SIMPLE
        level = LogLevel.BODY
    }

    defaultRequest {
        header("Accept", "application/json")
        header("Content-Type", "application/json")
    }
}