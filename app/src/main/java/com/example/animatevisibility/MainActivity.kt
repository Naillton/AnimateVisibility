package com.example.animatevisibility

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animatevisibility.ui.theme.AnimateVisibilityTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimateVisibilityTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
private fun MainScreen() {
    // criando estado booleano para guardar informacoes do botao ao clicar nele
    var state by rememberSaveable { mutableStateOf(true) }

    // funcao onClick usada para modificar o estado state
    val onClick = {it: Boolean ->
        state = it
    }

    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomButton(
                text = "Show", targetState = state, onClick = { onClick(!state) })

            Spacer(modifier = Modifier.height(20.dp))

            // AnimatedVisibility usado para mostrar o box caso o state seja true com animacao,
            // as animacoes podem ser usadas com efeitos de posicao ou rapidamente so chamando os metodos
            AnimatedVisibility(
                visible = state,
                // enter = fadeIn()
                // exit = fadeOut()
                /*enter = slideInVertically(
                    initialOffsetY = { 50 }
                ) + fadeIn(initialAlpha = 0.3f),
                exit = slideOutVertically() + fadeOut()*/
                enter = fadeIn(animationSpec = tween(2000)),
                exit = fadeOut(animationSpec = tween(2000))
            ) {
                Box(modifier = Modifier
                    .size(height = 200.dp, width = 200.dp)
                    .background(Color.Blue))
            }
        }
        
    }
}

// funcao de botao customizado
@Composable
private fun CustomButton(
    text: String,
    targetState: Boolean,
    onClick: (Boolean) -> Unit
) {
    Button(
        onClick = { onClick(targetState) },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(
                Random.nextInt(255),
                Random.nextInt(255),
                Random.nextInt(255),
                255
            ),
            contentColor = Color.White
        )
    ) {
        Text(text = text)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    AnimateVisibilityTheme {
        MainScreen()
    }
}