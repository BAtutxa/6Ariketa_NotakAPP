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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
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
    val izenak = listOf("PMDM", "DI", "SIS", "EIE", "AD", "H1", "H2")


    val notak = remember { Array(7) { Array(2) { mutableStateOf("") } } }


    var emaitza by remember { mutableStateOf("xxxxx") }


    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.irudia),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
        )


        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(start = 30.dp, end = 16.dp, top = 130.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "PAG2: Notak",
                fontSize = 24.sp,
                color = Color.Blue,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            )


            // Una fila por asignatura, recorriendo el array
            for (i in 0 until 7) {
                Fila(izenak[i], notak[i][0], notak[i][1])
            }


            Button(
                onClick = {
                    val denak = mutableListOf<Double>()
                    var guztiakOndo = true


                    // Recorrer el array 2D y pasar los textos a números
                    for (i in 0 until 7) {
                        for (j in 0 until 2) {
                            val n = notak[i][j].value.replace(',', '.').toDoubleOrNull()
                            if (n != null) denak.add(n) else guztiakOndo = false
                        }
                    }


                    emaitza = if (guztiakOndo) {
                        // Ordenar, quitar la más baja y la más alta, y hacer el promedio
                        val ordenatuta = denak.sorted()
                        val kenduta = ordenatuta.drop(1).dropLast(1)
                        "%.2f".format(kenduta.average())
                    } else {
                        "-"
                    }
                }
            ) {
                Text("Mediana Kalkulatu")
            }


            Text(
                text = "Batazbesteko Nota: " + emaitza,
                fontSize = 20.sp,
                color = Color.Blue
            )
        }
    }
}


// UNA FILA: Text (módulo) + TextField + TextField + Text (media de la fila)
@Composable
fun Fila(
    izena: String,
    nota1: MutableState<String>,
    nota2: MutableState<String>
) {
    val x = nota1.value.replace(',', '.').toDoubleOrNull()
    val y = nota2.value.replace(',', '.').toDoubleOrNull()
    val m: Double? = if (x != null && y != null) (x + y) / 2 else null


    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = izena, color = Color.Blue, modifier = Modifier.width(50.dp))


        TextField(
            value = nota1.value,
            onValueChange = { nota1.value = it },
            label = { Text("Nota 1") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.weight(1f)
        )


        TextField(
            value = nota2.value,
            onValueChange = { nota2.value = it },
            label = { Text("Nota 2") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.weight(1f)
        )


        Text(
            text = if (m != null) "%.2f".format(m) else "-",
            color = Color.Blue,
            modifier = Modifier.width(50.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NotakAPPTheme {
        NotakKalkulatu()
    }
}
