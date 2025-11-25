package com.example.laboratorio13_moviles

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Ejercicio2Screen(modifier: Modifier = Modifier) {
    var isBlue by remember { mutableStateOf(true) }

    val animatedColor by animateColorAsState(
        targetValue = if (isBlue) Color.Blue else Color.Green,
        animationSpec = tween(durationMillis = 600)
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Ejercicio 2: Benjamín Sullca",
            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
            modifier = Modifier.padding(bottom = 24.dp)
                .align(Alignment.CenterHorizontally)
        )
        Button(onClick = { isBlue = !isBlue }) {
            Text("Cambiar color")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .size(150.dp)
                .background(animatedColor)
        )
    }
}
