package com.example.notakapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
                    NotakFun(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun NotakFun(name: String, modifier: Modifier = Modifier) {

    val ikasgaiak = arrayOf("PMDM", "DI", "SIS", "EIE", "AD", "H1", "H2")

    var notak by remember {
        mutableStateOf(Array(7) { Array(2) { "" } })
    }
    // Dato
    var emaitza by remember {
        mutableStateOf("")
    }

    Box(modifier = modifier.fillMaxSize()) {

        Image(
            painter = painterResource(R.drawable.bermeo),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .background(
                    Color.White,
                    RoundedCornerShape(24.dp)
                )
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "PAG2: Notak",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            for (i in ikasgaiak.indices) {

                Text(
                    text = ikasgaiak[i],
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    OutlinedTextField(
                        value = notak[i][0],
                        onValueChange = { balioa ->
                            val berriak = notak.map {
                                it.copyOf()
                            }.toTypedArray()

                            berriak[i][0] = balioa
                            notak = berriak
                            emaitza = ""
                        },
                        label = { Text("Nota 1") },
                        modifier = Modifier.weight(1f),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Decimal
                        ),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = notak[i][1],
                        onValueChange = { balioa ->
                            val berriak = notak.map {
                                it.copyOf()
                            }.toTypedArray()

                            berriak[i][1] = balioa
                            notak = berriak
                            emaitza = ""
                        },
                        label = { Text("Nota 2") },
                        modifier = Modifier.weight(1f),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Decimal
                        ),
                        singleLine = true
                    )
                }

                val nota1 = notak[i][0]
                    .replace(',', '.').toDoubleOrNull()

                val nota2 = notak[i][1]
                    .replace(',', '.').toDoubleOrNull()

                val batezbestekoa =
                    if (nota1 != null && nota2 != null) {
                        (nota1 + nota2) / 2
                    } else {
                        null
                    }

                Text(
                    text = if (batezbestekoa != null) {
                        "Batazbesteko nota: ${
                            String.format("%.2f", batezbestekoa)
                        }"
                    } else {
                        "Batazbesteko nota: --"
                    },
                    fontSize = 14.sp
                )

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {

                    val notaGuztiak = notak.flatMap { lerro ->
                        lerro.map { testua ->
                            testua.replace(',', '.').toDoubleOrNull()
                        }
                    }

                    if (notaGuztiak.any { it == null }) {

                        emaitza = "Sartu 14 notak, mesedez."

                    } else {

                        val ordenatuta = notaGuztiak
                            .filterNotNull()
                            .sorted()

                        val kenduta = ordenatuta
                            .drop(1)
                            .dropLast(1)

                        val batazbestekoa =
                            kenduta.average()

                        emaitza = "Batazbesteko Nota: ${
                            String.format("%.2f", batazbestekoa)
                        }"
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Mediana Kalkulatu")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = emaitza,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1565C0)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotakFunPreview() {
    NotakAPPTheme {
        NotakFun("Android")
    }
}
