package com.danielcalebe.astrolog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun Sobre(nav: NavHostController) {
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
      .padding(24
        .dp)) {
      Button(
        onClick = {nav.navigate("home")},
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
      ) {
        Icon(Icons.Default.Home, null)
        Text("VOLTAR PARA HOME")
      }

    }
  }

}



