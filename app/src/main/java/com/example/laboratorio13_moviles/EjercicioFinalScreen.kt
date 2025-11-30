package com.example.laboratorio13_moviles

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.random.Random

enum class ShootState {
    Listo,
    Disparando,
    Gol,
    Fallo
}

@OptIn(ExperimentalAnimationApi::class) // ← REQUERIDO PARA AnimatedContent
@Composable
fun EjercicioFinalScreen(modifier: Modifier = Modifier) {

    var shootState by remember { mutableStateOf(ShootState.Listo) }
    var score by remember { mutableStateOf(0) }

    // 🎨 Color del campo según estado
    val fieldColor by animateColorAsState(
        targetValue = when (shootState) {
            ShootState.Listo -> Color(0xFF1B5E20)
            ShootState.Disparando -> Color(0xFF2E7D32)
            ShootState.Gol -> Color(0xFF004D40)
            ShootState.Fallo -> Color(0xFFB71C1C)
        },
        animationSpec = tween(500),
        label = "fieldColor"
    )

    // ⚽ Animación de posición de la pelota
    val ballOffsetY by animateDpAsState(
        targetValue = when (shootState) {
            ShootState.Disparando, ShootState.Gol, ShootState.Fallo -> (-150).dp
            else -> 0.dp
        },
        animationSpec = tween(700),
        label = "ballOffsetY"
    )

    // ⚽ Tamaño animado
    val ballSize by animateDpAsState(
        targetValue = if (shootState == ShootState.Listo) 48.dp else 32.dp,
        animationSpec = tween(700),
        label = "ballSize"
    )

    // 🌀 LÓGICA DE DISPARO
    LaunchedEffect(shootState) {
        if (shootState == ShootState.Disparando) {
            delay(700)

            val esGol = Random.nextBoolean()
            shootState = if (esGol) {
                score++
                ShootState.Gol
            } else ShootState.Fallo

            delay(900)
            shootState = ShootState.Listo
        }
    }

    // 🟩 LAYOUT PRINCIPAL
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(fieldColor)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        // Encabezado
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text(text = "Prototipo: Juego de Penales", color = Color.White)
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Puntaje: $score", color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))

            // 🌀 AnimatedContent corregido (incluye @OptIn)
            AnimatedContent(
                targetState = shootState,
                transitionSpec = {
                    fadeIn(animationSpec = tween(300)) with
                            fadeOut(animationSpec = tween(300))
                },
                label = "estadoTexto"
            ) { state ->

                val mensaje = when (state) {
                    ShootState.Listo -> "Listo para patear ⚽"
                    ShootState.Disparando -> "Disparando..."
                    ShootState.Gol -> "¡GOOOOL! 🎉"
                    ShootState.Fallo -> "¡Fallo! 😢"
                }

                Text(text = mensaje, color = Color.White)
            }
        }

        // ⚽ CANCHA
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp),
            contentAlignment = Alignment.BottomCenter
        ) {

            // Arco simple
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .width(220.dp)
                    .height(80.dp)
                    .background(Color.White.copy(alpha = 0.25f))
            )

            // ⚽ Pelota animada
            Box(
                modifier = Modifier
                    .offset(y = ballOffsetY)
                    .size(ballSize)
                    .background(Color.White, CircleShape)
            )
        }

        // Botón de disparo
        Button(
            onClick = {
                if (shootState == ShootState.Listo) {
                    shootState = ShootState.Disparando
                }
            },
            enabled = shootState == ShootState.Listo
        ) {
            Text(
                text = if (shootState == ShootState.Listo)
                    "Patear al arco"
                else
                    "Esperando..."
            )
        }
    }
}
