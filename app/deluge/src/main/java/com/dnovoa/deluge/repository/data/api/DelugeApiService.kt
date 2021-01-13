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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DelugeApiService(private val httpClient: HttpClient) {

    @KtorExperimentalAPI
    suspend fun login(): String {
        val response = httpClient.get<String> {
            url {
                takeFrom("https://en.wikipedia.org/wiki/Main_Page")
            }
        }

        /* val response = httpClient.request<HttpResponse>(
             url = URLBuilder("https://en.wikipedia.org/wiki/Main_Page").build()
         ) {
             method = HttpMethod.Get
         }*/

        /* val response = httpClient.post<HttpResponse>(
             url = URLBuilder(host = BASE_URL, port = 8112).build()
         ) {
             body = DelugeLoginRequestDto(
                 method = "auth.login",
                 params = "[0ipshahto]",
                 id = "1"
             )
         }
 */
        httpClient.close()

        return response.toString()
    }

    private fun HttpRequestBuilder.apiUrl() {
        url {
            takeFrom("https://en.wikipedia.org/wiki/Main_Page")
        }
    }
}

val BASE_URL = "http://192.168.1.144"