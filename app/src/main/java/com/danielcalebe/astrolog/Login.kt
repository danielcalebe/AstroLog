package com.danielcalebe.astrolog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.danielcalebe.astrolog.ui.theme.AstroLogTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun Login(nav: NavController) {

  var ctx = LocalContext.current
  var username by remember { mutableStateOf("") }
  var pass by remember { mutableStateOf("") }
  val scope = rememberCoroutineScope()
  var loading by remember { mutableStateOf(false) }
  var error by remember { mutableStateOf<String?>(null) }



  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .padding(vertical = 24.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Row(
      Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Image(
        painter = painterResource(R.drawable.logo),
        null,
        modifier = Modifier.size(200.dp),
      )

      Image(
        painter = painterResource(R.drawable.astro),
        null,
        modifier = Modifier.size(200.dp),
      )
    }

    Spacer(Modifier.weight(0.2f))
    Column(
      modifier = Modifier
        .fillMaxWidth(0.8f)
        .weight(1f),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
      Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Username", color = MaterialTheme.colorScheme.onSurface)
        OutlinedTextField(
          value = username,
          onValueChange = { username = it },
          colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
          ),
          placeholder = { Text("usuario01", color = Color.LightGray.copy(0.8f)) },
          modifier = Modifier.fillMaxWidth(),
          shape = RoundedCornerShape(12.dp),
        )
      }
      var showPass by remember { mutableStateOf(false) }
      Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Senha", color = MaterialTheme.colorScheme.onSurface)
        OutlinedTextField(
          value = pass,
          onValueChange = { pass = it },
          colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
          ),
          placeholder = { Text("*******", color = Color.LightGray.copy(0.8f)) },
          modifier = Modifier.fillMaxWidth(),
          shape = RoundedCornerShape(12.dp),
          visualTransformation = if (showPass) VisualTransformation.None else PasswordVisualTransformation(),
          trailingIcon = {
            IconButton({ showPass = !showPass }) {
              if (showPass) Icon(
                Icons.Default.VisibilityOff,
                null,
                tint = Color.LightGray.copy(0.8f)
              )
              else Icon(Icons.Default.Visibility, null, tint = Color.LightGray.copy(0.8f))
            }
          }
        )
      }

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
      ) {
        Text(error ?: "", color = MaterialTheme.colorScheme.onSurface)
      }

      Button(
        onClick = {
          scope.launch {
            loading = true
            delay(500)
            error = null
            val r = Api.login(username, pass)
            if (r.data != null) {
              Session.save(ctx, r.data.token)
              nav.navigate("home") {
                popUpTo("login"){
                  inclusive = true
                }
              }
            } else error = r.error
            loading = false
          }
        },
        enabled = username.isNotEmpty() && pass.isNotEmpty(),
        modifier = Modifier.fillMaxWidth()
      ) {
        if (loading) CircularProgressIndicator(
          trackColor = MaterialTheme.colorScheme.background,
          color = MaterialTheme.colorScheme.primary,
          modifier = Modifier.size(24.dp)
        )
        else Text("ENTRAR")
      }
    }
  }
}