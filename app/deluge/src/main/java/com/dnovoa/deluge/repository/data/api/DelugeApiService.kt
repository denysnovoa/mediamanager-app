package com.dnovoa.deluge.repository.data.api

import com.dnovoa.deluge.repository.data.api.model.DelugeRequestDto
import com.dnovoa.deluge.repository.data.storage.model.DelugeSessionDto
import com.dnovoa.deluge.repository.data.storage.model.DelugeUserId
import com.dnovoa.deluge.repository.data.storage.model.DelugeUserSession
import io.ktor.client.HttpClient
import io.ktor.client.request.cookie
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.http.takeFrom
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow

class DelugeApiService(private val httpClient: HttpClient) {

    var requestId = 0

    @ExperimentalCoroutinesApi
    fun login(): Flow<DelugeSessionDto?> {
        return channelFlow {
            HttpClient().use {
                val response = httpClient.post<HttpResponse> {
                    url {
                        takeFrom(BASE_URL_LOCAL)
                    }
                    body = DelugeRequestDto.Login(
                        method = "auth.login",
                        params = listOf("0ipshahto"),
                        id = requestId++.toString()
                    )
                }

                val sessionHeaderCookie = response.headers["Set-Cookie"].orEmpty()

                val delugeSession = if (response.status == HttpStatusCode.OK) {
                    DelugeSessionDto(
                        DelugeUserId(requestId),
                        DelugeUserSession(sessionHeaderCookie)
                    )
                } else {
                    null
                }

                channel.offer(delugeSession)
            }
        }
    }

    @ExperimentalCoroutinesApi
    fun updatedTorrentSpeed(speed: Int, session: DelugeSessionDto): Flow<Boolean> {
        return channelFlow {
            val body = DelugeRequestDto.SetConfig(
                method = "core.set_config",
                params = listOf(
                    mapOf(
                        Pair(
                            "max_download_speed",
                            speed.toString()
                        )
                    )
                ),
                id = requestId++.toString()
            )

            val response = callApi<HttpResponse>(session, body)

            channel.offer(response.status == HttpStatusCode.OK)
        }
    }

    private suspend inline fun <reified T> callApi(
        session: DelugeSessionDto,
        request: DelugeRequestDto
    ): T = HttpClient().use {
        httpClient.post {
            url {
                takeFrom(BASE_URL_LOCAL)
            }
            body = request
            cookie("Set-Cookie", session.userSession.session)
        }
    }

    fun getUiInfo(session: DelugeSessionDto): Flow<String> {
        return channelFlow {

            val body = DelugeRequestDto.Ui(
                method = "web.update_ui",
                params = arrayListOf(
                    listOf(
                        "max_download_speed"
                    )
                ),
                id = requestId++.toString()
            )

            val response = callApi<HttpResponse>(session, body)

            channel.offer(response.toString())
        }
    }
}

val BASE_URL_WIFI = "https://dnovoa20.duckdns.org"
val BASE_URL_LOCAL = "http://192.168.1.144:8112/json"