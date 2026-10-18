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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.graphics.evaluateCubic
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notakapp.ui.theme.NotakAPPTheme
import kotlin.collections.sorted

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
    Box(modifier.fillMaxSize()) {
        NotakKalkulatuFondoa()
        NotakKalkulatuTestua()
    }
}

@Composable
fun NotakKalkulatuFondoa(modifier: Modifier = Modifier) {
    val fondoa = painterResource(R.drawable.fondoariketa6)

    Image(
        painter = fondoa,
        contentDescription = null,
        modifier = modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun NotakKalkulatuTestua(modifier: Modifier = Modifier) {

    var bbEmaitza by remember { mutableStateOf("Batazbesteko Nota: --") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(36.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Spacer(modifier = modifier.height(16.dp))

        Text(
            text = "PAG2: Notak",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = modifier.height(36.dp))

        val moduluak = listOf("PMDM", "DI", "SIS", "EIE", "AD", "H1", "H2")

        val notakList = remember { List(7) { mutableStateListOf("", "") } }

        moduluak.forEachIndexed { index, modulua ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                //Modulu izena
                Text(
                    text = modulua,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(6.dp))

                TextField(
                    value = notakList[index][0],
                    onValueChange = { notakList[index][0] = it },
                    label = { Text("Nota 1", fontSize = 11.sp) },
                    modifier = Modifier.width(90.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Spacer(modifier = Modifier.width(4.dp))

                TextField(
                    value = notakList[index][1],
                    onValueChange = { notakList[index][1] = it },
                    label = { Text("Nota 2", fontSize = 11.sp) },
                    modifier = Modifier.width(90.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                val n1 = notakList[index][0].toDoubleOrNull() ?: 0.0
                val n2 = notakList[index][1].toDoubleOrNull() ?: 0.0
                val bbNota = (n1 + n2) / 2.0

                Text(
                    text = "Batazbestekoa %.2f".format(bbNota),
                    fontSize = 11.sp,
                    modifier = Modifier.weight(1.5f)
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            val notakZbk = mutableListOf<Double>()

            for (lerro in notakList) {
                for (nota in lerro) {
                    val zbk = nota.toDoubleOrNull()
                    if (zbk != null) {
                        notakZbk.add(zbk)
                    }
                }
            }
            //Zerrenda ordenatu
            notakZbk.sort()
            //Elementu txikiena ezabatu
            notakZbk.removeAt(0)
            //Elementu handiena ezabatu
            notakZbk.removeAt(notakZbk.size - 1)

            val batazBesteko = notakZbk.average()
            bbEmaitza = "Batazbesteko Nota: %.2f".format(batazBesteko)
        }) {
            Text("Mediana Kalkulatu")
        }

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = bbEmaitza,
            fontSize = 16.sp,
        )

    }
}


@Preview(showBackground = true)
@Composable
fun NotakKalkulatuPreview() {
    NotakAPPTheme {
        NotakKalkulatu()
    }
}