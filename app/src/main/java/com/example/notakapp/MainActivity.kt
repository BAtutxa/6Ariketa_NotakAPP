package com.example.notakapp

import android.os.Bundle
import androidx.activity.*
import androidx.activity.compose.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.*
import androidx.compose.foundation.text.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.layout.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
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
    val ikasgaiak = listOf("PMDM", "DI", "SIS", "EIE", "AD", "H1", "H2")

    val notak = remember {
        Array(7) {Array(2) {mutableStateOf("")} }
    }

    var medianaEm by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text="PAG2 Notak",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom=16.dp)
            )

            ikasgaiak.forEachIndexed { i, ikasgaia ->
                Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = ikasgaia,
                        modifier = Modifier.weight(1f),
                        fontWeight = FontWeight.Bold
                    )

                    OutlinedTextField(
                        value = notak[i][0].value,
                        onValueChange = { notak[i][0].value = it},
                        label = {Text("Nota 1")},
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.weight(1.2f).padding(horizontal = 2.dp)
                    )

                    OutlinedTextField(
                        value = notak[i][1].value,
                        onValueChange = { notak[i][1].value = it},
                        label = {Text("Nota 2")},
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.weight(1.2f).padding(horizontal = 2.dp)
                    )

                    val nota1 = notak[i][0].value.toDoubleOrNull()
                    val nota2 = notak[i][1].value.toDoubleOrNull()

                    val batazbestekoNota = if (nota1 != null && nota2 != null) {
                        String.format("%.2f", (nota1+nota2) / 2.0)
                    } else {
                        "- "
                    }

                    Text(
                        text = batazbestekoNota,
                        modifier = Modifier.weight(1f),
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    val notakM = mutableListOf<Double>()
                    for (i in 0..7) {
                        for (j in 0..7) {
                            notak[i][j].value.toDoubleOrNull()?.let {
                                notakM.add(it)
                            }
                        }
                    }

                    if (notakM.size >= 3) {
                        notakM.sort()

                        notakM.removeAt(0)
                        notakM.removeAt(notakM.size-1)

                        val batazb = notakM.average()
                        medianaEm = String.format("%.2f", batazb)
                    } else {
                        medianaEm = "-"
                    }
                }
            ){
                Text("Mediana kalkulatu")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Batazbesteko nota: $medianaEm",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    NotakAPPTheme {
        NotakKalkulatu()
    }
}