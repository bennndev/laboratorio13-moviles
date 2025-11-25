package com.example.laboratorio13_moviles

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.laboratorio13_moviles.ui.theme.Laboratorio13movilesTheme

enum class UiState {
    Cargando,
    Contenido,
    Error
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Ejercicio4Screen(modifier: Modifier = Modifier) {

    var currentState by remember { mutableStateOf(UiState.Cargando) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(onClick = {
            currentState = when (currentState) {
                UiState.Cargando -> UiState.Contenido
                UiState.Contenido -> UiState.Error
                UiState.Error -> UiState.Cargando
            }
        }) {
            Text("Cambiar Estado")
        }

        Spacer(modifier = Modifier.height(30.dp))

        AnimatedContent(
            targetState = currentState,
            transitionSpec = {
                fadeIn(animationSpec = tween(500)) with
                        fadeOut(animationSpec = tween(500))
            }
        ) { state ->

            when (state) {
                UiState.Cargando -> Text("⏳ Cargando...")
                UiState.Contenido -> Text("✅ Contenido cargado")
                UiState.Error -> Text("❌ Ocurrió un error")
            }
        }
    }
}
