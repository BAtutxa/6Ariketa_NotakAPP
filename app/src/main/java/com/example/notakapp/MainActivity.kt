package com.example.notakapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
    val moduluaIzenak = listOf("PMDM", "DI", "SIS", "EIE", "AD", "H1", "H2")

    var notakTestuak by remember {
        mutableStateOf(Array(7) { Array(2) { "" } })
    }

    var medianaEmaitza by remember { mutableStateOf<String?>(null) }

    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.fondoa),
            contentDescription = "Fondo irudia",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.4f))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "PAG2: Notak",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            moduluaIzenak.forEachIndexed { index, modulua ->
                val nota1 = notakTestuak[index][0].toDoubleOrNull()
                val nota2 = notakTestuak[index][1].toDoubleOrNull()

                val batazbestekoa = if (nota1 != null && nota2 != null) {
                    String.format("%.2f", (nota1 + nota2) / 2.0)
                } else {
                    "-"
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White.copy(alpha = 0.85f)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = modulua,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            modifier = Modifier.width(50.dp)
                        )

                        OutlinedTextField(
                            value = notakTestuak[index][0],
                            onValueChange = { balioa ->
                                val berria = Array(7) { i -> notakTestuak[i].clone() }
                                berria[index][0] = balioa
                                notakTestuak = berria
                            },
                            label = { Text("Nota 1", fontSize = 10.sp) },
                            modifier = Modifier.width(85.dp),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )

                        OutlinedTextField(
                            value = notakTestuak[index][1],
                            onValueChange = { balioa ->
                                val berria = Array(7) { i -> notakTestuak[i].clone() }
                                berria[index][1] = balioa
                                notakTestuak = berria
                            },
                            label = { Text("Nota 2", fontSize = 10.sp) },
                            modifier = Modifier.width(85.dp),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "Batazbestekoa", fontSize = 10.sp, color = Color.Gray)
                            Text(
                                text = batazbestekoa,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = Color(0xFF1E88E5)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    val notakArray2D = Array(7) { DoubleArray(2) { 0.0 } }
                    var denaBeteta = true

                    for (i in 0 until 7) {
                        val n1 = notakTestuak[i][0].toDoubleOrNull()
                        val n2 = notakTestuak[i][1].toDoubleOrNull()

                        if (n1 != null && n2 != null) {
                            notakArray2D[i][0] = n1
                            notakArray2D[i][1] = n2
                        } else {
                            denaBeteta = false
                            break
                        }
                    }

                    if (denaBeteta) {
                        val batazbestekoak = mutableListOf<Double>()
                        for (i in 0 until 7) {
                            val modBatazbestekoa = (notakArray2D[i][0] + notakArray2D[i][1]) / 2.0
                            batazbestekoak.add(modBatazbestekoa)
                        }

                        batazbestekoak.sort()
                        batazbestekoak.removeAt(0)
                        batazbestekoak.removeAt(batazbestekoak.size - 1)

                        val azkenPromedioa = batazbestekoak.average()
                        medianaEmaitza = String.format("%.2f", azkenPromedioa)
                    } else {
                        medianaEmaitza = "Errorea: Sartu nota guztiak"
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF388E3C)),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp)
            ) {
                Text(text = "Mediana Kalkulatu", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            medianaEmaitza?.let { emaitza ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        text = "Batazbesteko Nota: $emaitza",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NotakAPPTheme {
        NotakKalkulatu()
    }
}