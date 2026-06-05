package com.danielcalebe.astrolog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun Objetos(nav: NavHostController) {
  val ctx = LocalContext.current
  val registeredList by remember { mutableStateOf(Session.getRegisteredObjs(ctx)) }
  val loading by remember { mutableStateOf(false) }
  val error by remember { mutableStateOf<String?>(null) }
  val objs = remember { mutableStateListOf<Api.Objeto?>(null) }

  LaunchedEffect(Unit) {
    registeredList.let { list ->
      list?.forEach { item ->
        try {
          objs.add(Api.getObject(item.id, Session.token(ctx)).data as Api.Objeto?)
        } catch (e: Exception) {
          e.printStackTrace()
        }
      }
    }
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
    }

    Box(
      modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp), contentAlignment = Alignment.Center
    ) {
      Text(
        "OBJETOS REGISTRADOS",
        style = MaterialTheme.typography.titleLarge.copy(),
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )
    }

    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(12.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Row(Modifier.fillMaxWidth()) {
        Text(
          "${objs.size} de 88 objetos mapeados",
          color = MaterialTheme.colorScheme.tertiary,
          style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp)
        )
      }



      objs.let { list ->
        LazyColumn(
          modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .padding(12.dp),
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
          items(list) { item ->
            item?.let {
              Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface)
              ) {
                Row(
                  modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.SpaceBetween
                ) {
                  Column() {
                    Text(
                      "Obj ${item?.id}: ${item?.name}",
                      fontSize = 18.sp,
                      fontWeight = FontWeight.SemiBold
                    )
                    Text(
                      "${item?.category}",
                      color = MaterialTheme.colorScheme.onSurfaceVariant,
                      style = MaterialTheme.typography.labelLarge
                    )
                  }
                  Row() {
                    Icon(
                      Icons.Default.Done,
                      null,
                      tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text("Reg", color = MaterialTheme.colorScheme.onSurfaceVariant)
                  }
                }
              }
            }
          }
        }
      }

      Button(
        onClick = { nav.navigate("home") },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
      ) {
        Icon(Icons.Default.Home, null)
        Text("VOLTAR PARA HOME")
      }


    }

  }


}

