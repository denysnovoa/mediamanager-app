package com.dnovoa.deluge.repository.data.api

import com.dnovoa.deluge.repository.data.api.model.DelugeLoginRequestDto
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.content.TextContent
import io.ktor.http.ContentType
import io.ktor.http.URLBuilder
import kotlinx.coroutines.flow.Flow

class DelugeApiService(private val httpClient: HttpClient) {

    suspend fun login(): Flow<String> {

        return HttpClient().use {
            httpClient.post(
                url = URLBuilder(host = BASE_URL, port = 8112, encodedPath = "/json").build()
            ) {
                body = DelugeLoginRequestDto(
                    method = "auth.login",
                    params = "[0ipshahto]",
                    id = "1"
                )
            }
        }
    }
}

val BASE_URL = "http://192.168.1.144"