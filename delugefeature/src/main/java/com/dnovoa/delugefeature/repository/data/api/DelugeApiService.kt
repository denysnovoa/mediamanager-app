package com.dnovoa.delugefeature.repository.data.api

import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.content.TextContent
import io.ktor.http.ContentType
import io.ktor.http.URLBuilder

class DelugeApiService(private val httpClient: HttpClient) {

    suspend fun login(): String {
        return httpClient.post(
            url = URLBuilder(host = BASE_URL, port = 8112, encodedPath = "/json").build()
        ) {
            body = TextContent(
                "{method: \"auth.login\", params: [\"0ipshahto\"], id: 1}",
                contentType = ContentType.Text.Plain
            )
        }
    }
}

val BASE_URL = "http://192.168.1.144"