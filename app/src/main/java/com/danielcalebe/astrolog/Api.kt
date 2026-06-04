package com.danielcalebe.astrolog

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object Api {
  val client = HttpClient(Android) {
    install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }
  }
  private const val BASE = "http://10.0.2.2:8000/astro_a2"

  data class Ret<T>(
    val data: T? = null,
    val error: String? = null,
    val unauthorized: Boolean = false
  )

  suspend fun isServerAvailable() = try {
    val r = client.get("$BASE/status")
    if (r.status.value == 200) true
    else false
  } catch (e: Exception) {
    e.printStackTrace()
    false
  }

}