package com.danielcalebe.astrolog

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AppRegistration
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.Today
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun Sobre() {
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
    }


    Column(
      modifier = Modifier
        .fillMaxWidth()
        .weight(1f)
    ) {
      Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp), contentAlignment = Alignment.Center
        ) {
          Text(
            "SOBRE O APLICATIVO",
            style = MaterialTheme.typography.titleLarge.copy(),
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
        Text(
          "Este aplicativo foi criado para transformar o céu noturno em uma jornada de\n" +
              "descobertas. Registre cada objeto celeste que você observar — de nebulosas a\n" +
              "galáxias distantes — e acompanhe sua evolução como astrônomo amador.\n" +
              "O universo é vasto. Comece um objeto de cada vez", textAlign = TextAlign.Justify
        )
      }




      Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp), contentAlignment = Alignment.Center
        ) {
          Text(
            "COMO FUNCIONA",
            style = MaterialTheme.typography.titleLarge.copy(),
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
        Text(
          "O aplicativo possui 88 objetos celestes rotativos que são disponibilizados\n" +
              "progressivamente para você", textAlign = TextAlign.Justify
        )
      }

    }




    Row(modifier = Modifier
      .fillMaxWidth()
      .padding(24.dp)) {
      Button(
        onClick = {},
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
      ) {
        Icon(Icons.Default.Home, null)
        Text("VOLTAR PARA HOME")
      }

    }
  }

}



