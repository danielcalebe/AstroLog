package com.danielcalebe.astrolog

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.SerialName


object Api {
  val client = HttpClient(Android) {
    install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }
  }
  private const val BASE = "http://10.0.2.2:8000/astro_a2"

  @Serializable
  data class AuthBody(val username: String, val password: String)
  data class Res<T>(
    val data: T? = null,
    val error: String? = null,
    val unauthorized: Boolean = false
  )


  @Serializable
  data class LoginRespoinse(
    @SerialName("role")
    val role: String,
    @SerialName("token")
    val token: String,
    @SerialName("userid")
    val userid: Int
  )

  @Serializable
  data class Objeto(
    @SerialName("category")
    val category: String,
    @SerialName("constellation")
    val constellation: String,
    @SerialName("description")
    val description: String,
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("total_objects")
    val totalObjects: Int
  )

  suspend fun isServerAvailable() = try {
    val r = client.get("$BASE/status")
    if (r.status.value == 200) true
    else false
  } catch (e: Exception) {
    e.printStackTrace()
    false
  }

  suspend fun login(email: String, password: String) = try {
    val r = client.post("$BASE/login") {
      contentType(ContentType.Application.Json); setBody(
      AuthBody(
        email,
        password
      )
    )
    }
    when (r.status.value) {
      401 -> Res(error = "Credenciais inválidas")
      200, 201 -> Res(data = r.body<LoginRespoinse>())
      else -> Res(error = "Erro desconhecido")
    }
  } catch (e: Exception) {
    e.printStackTrace()
    Res(error = "Erro")
  }

  suspend fun getObject(id: Int, token: String?) = try {
    val r = client.get("$BASE/objeto/$id") { header(HttpHeaders.Authorization, "Bearer $token") }
    when (r.status.value) {
      401, 503 -> Res(unauthorized = true)
      200 -> Res<Objeto>(data = r.body<Objeto>())
      else -> Res("Erro desconhecido")
    }
  } catch (e: Exception) {
    Res(error = "Erro")
  }


}