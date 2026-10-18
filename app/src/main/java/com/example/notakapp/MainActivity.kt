package com.example.notakapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notakapp.ui.theme.NotakAPPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotakAPPTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NotakKalkulatu(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun NotakKalkulatu(modifier: Modifier = Modifier) {

    val asignaturas = listOf("PMDM", "DI", "SIS", "EIE", "AD", "H1", "H2")

    // Array bidimensional: notas[asignatura][0 = nota 1, 1 = nota 2]
    val notas = remember { List(7) { mutableStateListOf("", "") } }
    var resultado by remember { mutableStateOf("") }

    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.pizarra),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(top = 40.dp, start = 24.dp, end = 24.dp, bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "PAG2: Notak",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(Modifier.height(16.dp))

            asignaturas.forEachIndexed { i, nombre ->
                val n1 = notas[i][0].toDoubleOrNull()
                val n2 = notas[i][1].toDoubleOrNull()
                val media = if (n1 != null && n2 != null) "%.1f".format((n1 + n2) / 2) else "-"

                FilaAsignatura(
                    nombre = nombre,
                    nota1 = notas[i][0],
                    nota2 = notas[i][1],
                    media = media,
                    onNota1Change = { notas[i][0] = it },
                    onNota2Change = { notas[i][1] = it }
                )
                Spacer(Modifier.height(4.dp))
            }

            Spacer(Modifier.height(16.dp))

            Button(onClick = {
                // Media de cada asignatura (solo las que tienen las 2 notas)
                val medias = asignaturas.indices.mapNotNull { i ->
                    val a = notas[i][0].toDoubleOrNull()
                    val b = notas[i][1].toDoubleOrNull()
                    if (a != null && b != null) (a + b) / 2 else null
                }
                // Quitar la más baja y la más alta, y promediar el resto
                resultado = if (medias.size > 2) {
                    "%.2f".format(medias.sorted().drop(1).dropLast(1).average())
                } else {
                    "Faltan notas"
                }
            }) {
                Text("Mediana Kalkulatu")
            }

            Spacer(Modifier.height(12.dp))

            Text(
                text = "Batazbesteko Nota: $resultado",
                color = Color.White,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun FilaAsignatura(
    nombre: String,
    nota1: String,
    nota2: String,
    media: String,
    onNota1Change: (String) -> Unit,
    onNota2Change: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(nombre, color = Color.White, modifier = Modifier.weight(1f))
        TextField(
            value = nota1,
            onValueChange = onNota1Change,
            label = { Text("Nota 1") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.weight(1.5f)
        )
        TextField(
            value = nota2,
            onValueChange = onNota2Change,
            label = { Text("Nota 2") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.weight(1.5f)
        )
        Text(media, color = Color.White, modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NotakAPPTheme {
        NotakKalkulatu()
    }
}