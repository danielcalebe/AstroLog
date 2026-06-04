package com.danielcalebe.astrolog

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danielcalebe.astrolog.ui.theme.AstroLogTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Splash(onClose: () -> Unit, navigate: () -> Unit) {


  val progress = remember { Animatable(0f) }
  var error by remember { mutableStateOf(false) }


  LaunchedEffect(Unit) {
    delay(200)
    val r = Api.isServerAvailable()
    if (r) {progress.animateTo(1f, tween(3000, 100, LinearEasing)); navigate();}
    else {error = true}
  }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Image(
      painter = painterResource(R.drawable.logo),
      null,
      alpha = progress.value * 1.5f,
      modifier = Modifier.size(200.dp),
    )
    Spacer(Modifier.height(48.dp))
    LinearProgressIndicator(
      progress = { progress.value },
      color = MaterialTheme.colorScheme.primary,
      trackColor = MaterialTheme.colorScheme.onSurface,
      strokeCap = StrokeCap.Round,
      modifier = Modifier
        .fillMaxWidth(0.5f)
        .height(24.dp),
      gapSize = (-24).dp,
      drawStopIndicator = {}
    )

    AnimatedVisibility(error) {
      BasicAlertDialog(
        onDismissRequest = {}
      ) {
        Column(
          modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(12.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Text(
            "Erro, não foi possível se conectar ao servidor! Tente novamente mais tarde",
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.SemiBold
          )
          Image(painter = painterResource(R.drawable.ovni), null, modifier = Modifier.size(200.dp))
          Spacer(Modifier.height(12.dp))
          Button(
            onClick = { onClose() },
            colors = ButtonDefaults.buttonColors(
              containerColor = MaterialTheme.colorScheme.onSurface
            ),
            modifier = Modifier.fillMaxWidth()
          ) {
            Text("Fechar o app", color = MaterialTheme.colorScheme.background)
          }
        }
      }
    }
  }
}