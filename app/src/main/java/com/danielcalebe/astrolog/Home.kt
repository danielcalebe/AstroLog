package com.danielcalebe.astrolog

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AppRegistration
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.Today
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import kotlinx.coroutines.time.delay

@Composable
fun Home(nav: NavHostController) {


  var loading by remember { mutableStateOf(false) }
  var updater by remember { mutableStateOf(false) }
  var error by remember { mutableStateOf<String?>(null) }
  val ctx = LocalContext.current
  var currentId by remember { mutableStateOf<Int>(Session.currentObject(ctx)) }
  var registeredObjects by remember { mutableStateOf(Session.getRegisteredObjs(ctx = ctx)) }

  fun updateRegObj() {
    registeredObjects = Session.getRegisteredObjs(ctx)
  }
  LaunchedEffect(currentId) { Session.incrementCurrentObject(ctx, currentId) }

  var dailyObj by remember { mutableStateOf<Api.Objeto?>(null) }

  LaunchedEffect(updater) {
    dailyObj = null
    loading = true
    delay(500)
    val r = Api.getObject(currentId, Session.token(ctx))
    if (r.data != null) dailyObj = r.data as Api.Objeto
    else if (r.unauthorized) {
      Session.logout(ctx); Toast.makeText(ctx, "Erro, Sessão expirada!", Toast.LENGTH_SHORT)
        .show(); nav.navigate("login")
    } else if (r.error != null) error = r.error
    loading = false
  }






  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.surface)
        .padding(12.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Text(
        "ASTRO LOG",
        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
      )
      TextButton(onClick = { if (!loading) Session.logout(ctx); nav.navigate("login") }
      ) {
        Icon(Icons.Default.Logout, null)
        Text("Logout")
      }
    }

    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(12.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
      Text(
        "Objeto atual: $currentId de 88",
        color = MaterialTheme.colorScheme.tertiary,
        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp)
      )
      Button(
        onClick = {
          if (!loading) updater = !updater
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
      ) {
        Icon(Icons.Default.Today, null)
        Text("VER OBJETO DO DIA")
      }

      Card(
        modifier = Modifier
          .fillMaxWidth()
          .border(
            1.dp, MaterialTheme.colorScheme.onSurfaceVariant,
            RoundedCornerShape(12.dp)
          ),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(12.dp)
      ) {

        Column(modifier = Modifier.padding(vertical = 24.dp, horizontal = 12.dp)) {


          if (loading) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
              CircularProgressIndicator()
            }
          }
          dailyObj?.let {
            Text(it.name, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
            Text(it.description)
            if (registeredObjects?.contains(RegisteredObj(it.id)) == true) {
              Button(
                onClick = { Session.toggleRegisterObject(ctx, it.id); updateRegObj();  registeredObjects = Session.getRegisteredObjs(ctx) },
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onSurfaceVariant),
                shape = RoundedCornerShape(12.dp)
              ) {
                Icon(
                  Icons.Default.Done,
                  null,
                  tint = MaterialTheme.colorScheme.background
                ); Text("Registrado", color = MaterialTheme.colorScheme.background)
              }
            } else {
              Button(
                onClick = { Session.toggleRegisterObject(ctx, it.id); updateRegObj(); registeredObjects = Session.getRegisteredObjs(ctx) },
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.background),
                shape = RoundedCornerShape(12.dp)
              ) {
                Text("Registrar", color = MaterialTheme.colorScheme.onSurfaceVariant)
              }
            }

          }
        }
      }

      Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
      ) {
        Column(
          modifier = Modifier
            .weight(1f)

            .clickable {
              if (!loading) currentId++
            }
            .clip(RoundedCornerShape(12.dp))
            .border(
              1.dp, MaterialTheme.colorScheme.onSurfaceVariant,
              RoundedCornerShape(12.dp)
            )
            .background(MaterialTheme.colorScheme.surface)
            .padding(12.dp),
          verticalArrangement = Arrangement.spacedBy(8.dp),
          horizontalAlignment = Alignment.Start
        ) {
          Text("Próximo objeto")
          Icon(Icons.Default.SkipNext, null)
        }

        Column(
          modifier = Modifier
            .weight(1f)
            .clickable {
              nav.navigate("objetos")
            }
            .clip(RoundedCornerShape(12.dp))
            .border(
              1.dp, MaterialTheme.colorScheme.onSurfaceVariant,
              RoundedCornerShape(12.dp)
            )
            .background(MaterialTheme.colorScheme.surface)
            .padding(12.dp),
          verticalArrangement = Arrangement.spacedBy(8.dp),
          horizontalAlignment = Alignment.Start
        ) {
          Text("Objetos registrados", maxLines = 2)
          Icon(Icons.Default.AppRegistration, null)
        }

        Column(
          modifier = Modifier
            .weight(1f)
            .clickable {
              nav.navigate("sobre")
            }
            .clip(RoundedCornerShape(12.dp))
            .border(
              1.dp, MaterialTheme.colorScheme.onSurfaceVariant,
              RoundedCornerShape(12.dp)
            )
            .background(MaterialTheme.colorScheme.surface)
            .padding(12.dp),
          verticalArrangement = Arrangement.spacedBy(8.dp),
          horizontalAlignment = Alignment.Start
        ) {
          Text("Sobre o Aplicativo")
          Icon(Icons.Default.Info, null)
        }
      }



      Image(
        painter = painterResource(R.drawable.ship),
        null,
        modifier = Modifier
          .fillMaxSize(0.7f)
          .offset(x = 120.dp, y = 72.dp),
        alpha = 0.2f
      )
    }

  }


}

