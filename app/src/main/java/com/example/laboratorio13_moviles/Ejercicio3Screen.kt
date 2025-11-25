package com.example.laboratorio13_moviles

import androidx.compose.animation.core.tween
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.laboratorio13_moviles.ui.theme.Laboratorio13movilesTheme

@Composable
fun Ejercicio3Screen(modifier: Modifier = Modifier) {

    var moved by remember { mutableStateOf(false) }

    val size: Dp by animateDpAsState(
        targetValue = if (moved) 180.dp else 100.dp,
        animationSpec = tween(600)
    )

    val offsetX: Dp by animateDpAsState(
        targetValue = if (moved) 120.dp else 0.dp,
        animationSpec = tween(600)
    )

    val offsetY: Dp by animateDpAsState(
        targetValue = if (moved) 150.dp else 0.dp,
        animationSpec = tween(600)
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Button(onClick = { moved = !moved }) {
            Text("Mover / Cambiar Tamaño")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .offset(x = offsetX, y = offsetY)
                .size(size)
                .background(Color.Magenta)
        )
    }
}
