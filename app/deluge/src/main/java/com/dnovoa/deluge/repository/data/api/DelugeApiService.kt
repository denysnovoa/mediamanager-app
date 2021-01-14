package com.dnovoa.deluge.repository.data.api

import com.dnovoa.deluge.repository.data.api.model.DelugeLoginRequestDto
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.content.TextContent
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.URLBuilder
import io.ktor.http.takeFrom
import io.ktor.util.KtorExperimentalAPI
import io.ktor.util.url
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DelugeApiService(private val httpClient: HttpClient) {

    fun login(): Flow<String> {
        return flow {
            HttpClient().use {
                /*     val response = httpClient.get<String> {
                       url {
                           takeFrom("https://en.wikipedia.org/wiki/Main_Page")
                       }
                   }
                   emit(response)
               }
               */

                val response = httpClient.post<String>{
                    url {
                        takeFrom("http://192.168.1.144:8112/json")
                    }
                    body = DelugeLoginRequestDto(
                        method = "auth.login",
                        params = "[0ipshahto]",
                        id = "1"
                    )
                }

                emit(response)
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