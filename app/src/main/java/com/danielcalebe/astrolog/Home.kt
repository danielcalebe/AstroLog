package com.danielcalebe.astrolog

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun Home() {
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
      TextButton(
        onClick = {}
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
        "Objeto atual: 3 de 88",
        color = MaterialTheme.colorScheme.tertiary,
        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp)
      )
      Button(
        onClick = {},
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
          Text("Nebulosa de Ório", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
          Text("Regiao de Órioo formação estela na constelação. Possui brilho intenso visível a olho nu")
          Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onSurfaceVariant),
            shape = RoundedCornerShape(12.dp)
          ) {Icon(Icons.Default.Done, null, tint = MaterialTheme.colorScheme.background); Text("Registrado", color = MaterialTheme.colorScheme.background) }
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
        modifier = Modifier.fillMaxSize(0.7f).offset(x = 120.dp, y = 72.dp),
        alpha = 0.2f
      )
    }

  }


}

