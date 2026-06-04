package com.danielcalebe.astrolog

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowInsetsCompat.Type
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.danielcalebe.astrolog.ui.theme.AstroLogTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      val nav = rememberNavController()
      val currentRoute = nav.currentBackStackEntryAsState().value?.destination?.route
      val ctx = LocalContext.current
      val wcc = WindowInsetsControllerCompat(window, window.decorView)
      if (currentRoute == "splash") {
        wcc.hide(Type.systemBars())
        wcc.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
      }
      AstroLogTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          Column(
            modifier = Modifier.padding(innerPadding)
          ) {
            LaunchedEffect(Unit) {
              Log.d("mystatus", Api.isServerAvailable().toString())
            }
            NavHost(nav, "splash") {
              composable("splash") {
                Splash(
                  onClose = { finish() },
                  navigate = {
                    if (Session.token(ctx) == null)
                      nav.navigate("login") {
                        popUpTo("splash") {
                          inclusive = true
                        }
                      }
                    else
                      nav.navigate("home") {
                        popUpTo("splash") {
                          inclusive = true
                        }
                      }
                  })
              }
              composable("login") { Login(nav) }
              composable("home") { Home(ctx, nav) }
              composable("observacoes") {}
              composable("sobre") {}
            }
          }
        }
      }
    }
  }
}