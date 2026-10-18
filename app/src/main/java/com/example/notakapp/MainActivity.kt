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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
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
    val moduluak = listOf("PMDM", "DI", "SIS", "EIE", "AD", "H1", "H2")

    val notak = remember {
        List(7) {List(2) { mutableStateOf("") } }
    }

    var mediaEmaitza by remember { mutableStateOf("") }

    Box (modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        val image = painterResource(R.drawable.ariketa6_fondoa)
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "PAG2: Notak",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row() {
                Text("PDMD: ")

                Column() {
                    TextField(
                        value = notak[0][0].value,
                        onValueChange = { notak [0][0].value = it},
                        label = { Text(" Nota1 ")}
                    )

                    TextField(
                        value = notak[0][1].value,
                        onValueChange = { notak [0][1].value = it},
                        label = { Text(" Nota2 ")}
                    )
                    val nota1 = notak[0][0].value.toDoubleOrNull()
                    val nota2 = notak[0][1].value.toDoubleOrNull()
                    val mediaPDMD = if (nota1 != null && nota2 != null) (nota1 + nota2) / 2.0 else "-"
                    Text("Media: $mediaPDMD")
                }
            }

            Row() {
                Text("DI: ")

                Column() {
                    TextField(
                        value = notak[1][0].value,
                        onValueChange = { notak [1][0].value = it},
                        label = { Text(" Nota1 ")}
                    )

                    TextField(
                        value = notak[1][1].value,
                        onValueChange = { notak [1][1].value = it},
                        label = { Text(" Nota2 ")}
                    )
                    val nota1 = notak[1][0].value.toDoubleOrNull()
                    val nota2 = notak[1][1].value.toDoubleOrNull()
                    val mediaDI = if (nota1 != null && nota2 != null) (nota1 + nota2) / 2.0 else "-"
                    Text("Media: $mediaDI")
                }
            }

            Row() {
                Text("SIS: ")

                Column() {
                    TextField(
                        value = notak[2][0].value,
                        onValueChange = { notak [2][0].value = it},
                        label = { Text(" Nota1 ")}
                    )

                    TextField(
                        value = notak[2][1].value,
                        onValueChange = { notak [2][1].value = it},
                        label = { Text(" Nota2 ")}
                    )
                    val nota1 = notak[2][0].value.toDoubleOrNull()
                    val nota2 = notak[2][1].value.toDoubleOrNull()
                    val mediaSIS = if (nota1 != null && nota2 != null) (nota1 + nota2) / 2.0 else "-"
                    Text("Media: $mediaSIS")
                }
            }

            Row() {
                Text("EIE: ")

                Column() {
                    TextField(
                        value = notak[3][0].value,
                        onValueChange = { notak [3][0].value = it},
                        label = { Text(" Nota1 ")}
                    )

                    TextField(
                        value = notak[3][1].value,
                        onValueChange = { notak [3][1].value = it},
                        label = { Text(" Nota2 ")}
                    )
                    val nota1 = notak[3][0].value.toDoubleOrNull()
                    val nota2 = notak[3][1].value.toDoubleOrNull()
                    val mediaEIE = if (nota1 != null && nota2 != null) (nota1 + nota2) / 2.0 else "-"
                    Text("Media: $mediaEIE")
                }
            }

            Row() {
                Text("AD: ")

                Column() {
                    TextField(
                        value = notak[4][0].value,
                        onValueChange = { notak [4][0].value = it},
                        label = { Text(" Nota1 ")}
                    )

                    TextField(
                        value = notak[4][1].value,
                        onValueChange = { notak [4][1].value = it},
                        label = { Text(" Nota2 ")}
                    )
                    val nota1 = notak[4][0].value.toDoubleOrNull()
                    val nota2 = notak[4][1].value.toDoubleOrNull()
                    val mediaAD = if (nota1 != null && nota2 != null) (nota1 + nota2) / 2.0 else "-"
                    Text("Media: $mediaAD")
                }
            }

            Row() {
                Text("H1: ")

                Column() {
                    TextField(
                        value = notak[5][0].value,
                        onValueChange = { notak [5][0].value = it},
                        label = { Text(" Nota1 ")}
                    )

                    TextField(
                        value = notak[5][1].value,
                        onValueChange = { notak [5][1].value = it},
                        label = { Text(" Nota2 ")}
                    )
                    val nota1 = notak[5][0].value.toDoubleOrNull()
                    val nota2 = notak[5][1].value.toDoubleOrNull()
                    val mediaH1 = if (nota1 != null && nota2 != null) (nota1 + nota2) / 2.0 else "-"
                    Text("Media: $mediaH1")
                }
            }

            Row() {
                Text("H2: ")

                Column() {
                    TextField(
                        value = notak[6][0].value,
                        onValueChange = { notak [6][0].value = it},
                        label = { Text(" Nota1 ")}
                    )

                    TextField(
                        value = notak[6][1].value,
                        onValueChange = { notak [6][1].value = it},
                        label = { Text(" Nota2 ")}
                    )
                    val nota1 = notak[6][0].value.toDoubleOrNull()
                    val nota2 = notak[6][1].value.toDoubleOrNull()
                    val mediaH2 = if (nota1 != null && nota2 != null) (nota1 + nota2) / 2.0 else "-"
                    Text("Media: $mediaH2")
                }
            }

            Button(
                onClick = {
                    val notenLista = mutableListOf<Double>()

                    for(i in 0..6){
                        for(j in 0..1){
                            val nota = notak[i][j].value.toDoubleOrNull()
                            if(nota != null){
                                notenLista.add(nota)
                            }
                        }
                    }

                    if (notenLista.size > 2) {
                        notenLista.sort()
                        val subLista = notenLista.subList(1, notenLista.size - 1)
                        mediaEmaitza = String.format("%.2f", subLista.average())
                    } else {
                        mediaEmaitza = "Notak falta dira"
                    }
                }
            ) {
                Text("Mediana kalkulatu")
            }

            Text(
                text = "Batazbesteko nota:  $mediaEmaitza",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp)

            )
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