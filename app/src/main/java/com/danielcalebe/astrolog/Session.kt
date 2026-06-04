package com.danielcalebe.astrolog

import android.content.Context
import androidx.core.content.edit
import kotlinx.coroutines.flow.MutableStateFlow

object Session {
  val authenticated = MutableStateFlow<Boolean>(false)
  private const val P = "p"; private const val K = "k";
  fun save(ctx: Context, t: String){ ctx.getSharedPreferences(P, 0).edit{ putString(K, t)}; authenticated.value = true}
  fun token(ctx: Context) = ctx.getSharedPreferences(P, 0).getString(K, null)
  fun init(ctx: Context){authenticated.value = token(ctx) != null}
  fun logout(ctx: Context){ ctx.getSharedPreferences(P,0).edit { remove(K) }; authenticated.value = false}
}