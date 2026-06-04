package com.danielcalebe.astrolog

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController

@Composable
fun Home(ctx: Context, nav: NavHostController) {
  LaunchedEffect(Unit) {
    val r = Api.getObject(1, Session.token(ctx))
    Log.d("response", r.toString())
    if (r.unauthorized) nav.navigate("login")
  }











}

