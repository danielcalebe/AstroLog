package com.danielcalebe.astrolog

import android.content.Context
import android.util.Log
import androidx.core.content.edit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

object Session {
  val authenticated = MutableStateFlow<Boolean>(false)
  private const val P = "p";
  private const val K = "k";
  fun save(ctx: Context, t: String) {
    ctx.getSharedPreferences(P, 0).edit { putString(K, t) }; authenticated.value = true
  }

  fun saveData(ctx: Context, t: Api.LoginRespoinse) {
    ctx.getSharedPreferences(P, 0)
      .edit { putString("current_user", Json.encodeToString(t)) }; authenticated.value = true
  }

  fun token(ctx: Context) = ctx.getSharedPreferences(P, 0).getString(K, null)
  fun getData(ctx: Context): Api.LoginRespoinse? {
    try {
      return ctx.getSharedPreferences(P, 0).getString("current_user", "")
        ?.let { Json.decodeFromString<Api.LoginRespoinse>(it) }
    } catch (e: Exception) {
      return null
    }
  }

  fun init(ctx: Context) {
    authenticated.value = token(ctx) != null
  }

  fun logout(ctx: Context) {
    ctx.getSharedPreferences(P, 0).edit { remove(K) }; authenticated.value = false
  }

  fun currentObject(ctx: Context) =
    ctx.getSharedPreferences(P, 0).getInt("current_obj_${getData(ctx)?.userid ?: 1}", 1)

  fun incrementCurrentObject(ctx: Context, newValue: Int) {
    ctx.getSharedPreferences(P, 0)
      .edit { putInt("current_obj_${getData(ctx)?.userid ?: 1}", newValue) }
  }

  fun getRegisteredObjs(ctx: Context): List<RegisteredObj>? {
    try {
      val string =
        ctx.getSharedPreferences(P, 0).getString("registered_${getData(ctx)?.userid ?: 1}", null)
      string?.let { return Json.decodeFromString<List<RegisteredObj>>(string) }
      return null
    } catch (e: Exception) {
      e.printStackTrace()
      return null
    }
  }


  fun toggleRegisterObject(ctx: Context, obj: Int) {
    val list = mutableListOf<RegisteredObj>()
    getRegisteredObjs(ctx)?.let { list.addAll(it) }
    if (list.contains(RegisteredObj(obj))) {
      list.remove(RegisteredObj(obj))
    } else {
      list.add(RegisteredObj(obj))
    }

    Log.d("olazinho", list.toString())
    ctx.getSharedPreferences(P, 0).edit {
      putString(
        "registered_${getData(ctx)?.userid ?: 1}", Json.encodeToString(
          list
        )
      )
    }
  }

}


@Serializable
data class RegisteredObj(val id: Int)