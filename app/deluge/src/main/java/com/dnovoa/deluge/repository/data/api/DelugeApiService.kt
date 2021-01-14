package com.dnovoa.deluge.repository.data.api

import com.dnovoa.deluge.repository.data.api.model.DelugeLoginRequestDto
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.http.takeFrom
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow

class DelugeApiService(private val httpClient: HttpClient) {

    @ExperimentalCoroutinesApi
    fun login(): Flow<String> {
        return channelFlow {
            HttpClient().use {
                val response = httpClient.post<String> {
                    url {
                        takeFrom("http://192.168.1.144:8112/json")
                    }
                    body = DelugeLoginRequestDto(
                        method = "auth.login",
                        params = "[0ipshahto]",
                        id = "1"
                    )
                }

                channel.offer(response)
            }
        }
    }

    private fun HttpRequestBuilder.apiUrl() {
        url {
            takeFrom("https://en.wikipedia.org/wiki/Main_Page")
        }
    }
}

val BASE_URL_WIFI = "http://dnovoa20.duckdns.org"
val BASE_URL_LOCAL = "http://192.168.1.144"