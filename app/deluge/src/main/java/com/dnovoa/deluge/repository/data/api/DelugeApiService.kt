package com.dnovoa.deluge.repository.data.api

import com.dnovoa.deluge.repository.data.api.model.DelugeLoginRequestDto
import com.dnovoa.deluge.repository.data.storage.model.DelugeSessionDto
import com.dnovoa.deluge.repository.data.storage.model.DelugeUserId
import com.dnovoa.deluge.repository.data.storage.model.DelugeUserSession
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.takeFrom
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow

class DelugeApiService(private val httpClient: HttpClient) {

    @ExperimentalCoroutinesApi
    fun login(): Flow<DelugeSessionDto> {
        return channelFlow {
            HttpClient().use {
                val response = httpClient.post<HttpResponse> {
                    url {
                        takeFrom("http://192.168.1.144:8112/json")
                    }
                    body = DelugeLoginRequestDto(
                        method = "auth.login",
                        params = listOf("0ipshahto"),
                        id = "1"
                    )
                }

                val sessionHeaderCookie = response.headers["Set-Cookie"].orEmpty()

                channel.offer(
                    DelugeSessionDto(
                        DelugeUserId(1),
                        DelugeUserSession(sessionHeaderCookie)
                    )
                )
            }
        }
    }
}

val BASE_URL_WIFI = "https://dnovoa20.duckdns.org"
val BASE_URL_LOCAL = "https://192.168.1.144"