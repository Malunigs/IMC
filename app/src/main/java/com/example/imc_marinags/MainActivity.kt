package com.example.imc_marinags

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import com.example.imc_marinags.ui.theme.IMC_MarinaGSTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IMC_MarinaGSTheme {
                IMCLayout()
            }
        }
    }
}

@Composable
fun IMCLayout() {
    var peso by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }
    var imcResult by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        //campo de texto para ingresar el peso
        TextField(
            value = peso,
            onValueChange = { peso = it },
            label = { Text("Peso (kg)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        //campo de texto para ingresar la altura
        TextField(
            value = altura,
            onValueChange = { altura = it },
            label = { Text("Altura (m)") },
            keyboardOptions =  KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        //bton para calcular el IMC
        Button(
            onClick = {
                val pesoValue = peso.toDoubleOrNull() ?: 0.0
                val alturaValue = altura.toDoubleOrNull() ?: 0.0
                val imc = calculateIMC(pesoValue, alturaValue)
                imcResult = "IMC: ${"%.2f".format(imc)}"
                category = categorizeIMC(imc)
            },
            modifier = Modifier.padding(bottom = 16.dp),
            shape = MaterialTheme.shapes.small.copy(all = CornerSize(7.dp)),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary.copy(green = 0.8f, alpha = 0.9f) //el color
            )
        ) {
            Text("Calcular IMC")
        }

        Text(text = imcResult)

        Text(text = category)

        when (category) {
            "Bajo peso" -> Image(painter = painterResource(id = R.drawable.pesopordebajo), contentDescription = "Bajo peso")
            "Normal" -> Image(painter = painterResource(id = R.drawable.pesonormal), contentDescription = "Normal")
            "Sobrepeso" -> Image(painter = painterResource(id = R.drawable.sobrepeso), contentDescription = "Sobrepeso")
        }
    }
}

@VisibleForTesting
internal fun calculateIMC(peso: Double, altura: Double): Double {
    return if (peso > 0 && altura > 0) {
        peso / (altura * altura)
    } else {
        0.0
    }
}

@VisibleForTesting
internal fun categorizeIMC(imc: Double): String {
    return when {
        imc < 18.5 -> "Bajo peso"
        imc < 24.9 -> "Normal"
        else -> "Sobrepeso"
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    IMC_MarinaGSTheme {
        IMCLayout()
    }
}
