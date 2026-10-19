package com.example.notakapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.notakapp.ui.theme.NotakAPPTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp


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
    var notak: Array<Array<Int>> by remember { mutableStateOf(Array(7)
    { Array(3) { 0 } }) }
    var batazbesteko : Double by remember {mutableStateOf(0.0) }


    Box(
        modifier = Modifier.fillMaxSize()
            .padding(30.dp)
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "PMDM",
                    modifier = Modifier.weight(1f) // Le damos un poco más de espacio a los inputs

                )
                TextField(
                    value = notak[0][0].toString(),
                    onValueChange = { texto ->
                        notak[0][0] = texto.toIntOrNull() ?: 0
                        notak = notak.clone()
                                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f)
                )
                TextField(
                    value = "${notak[0][1]}",
                    onValueChange = { texto ->
                        notak[0][1] = texto.toIntOrNull() ?: 0
                        notak = notak.clone()
                                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f)
                    )
                Text(
                    text = "Nota: "+kalkulatuNota ( 0, notak),
                    modifier = Modifier.weight(1f) // Le damos un poco más de espacio a los inputs

                )
            }
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "DI",
                    modifier = Modifier.weight(1f)
                )
                TextField(
                    value = notak[1][0].toString(),
                    onValueChange = { texto ->
                        notak[1][0] = texto.toIntOrNull() ?:0
                        notak = notak.clone()
                                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f)
                )
                TextField(
                    value = "${notak[1][1]}",
                    onValueChange = { texto ->
                        notak[1][1] = texto.toIntOrNull() ?: 0
                        notak = notak.clone()
                                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Nota: "+kalkulatuNota ( 1, notak),
                    modifier = Modifier.weight(1f)
                )
            }
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "SIS",
                    modifier = Modifier.weight(1f)
                )
                TextField(
                    value = notak[2][0].toString(),
                    onValueChange = { texto ->
                        notak[2][0] = texto.toIntOrNull() ?: 0
                        notak = notak.clone()
                                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f)
                )
                TextField(
                    value = "${notak[2][1]}",
                    onValueChange = { texto ->
                        notak[2][1] = texto.toIntOrNull() ?: 0
                        notak = notak.clone()
                                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Nota: "+kalkulatuNota ( 2   , notak),
                    modifier = Modifier.weight(1f)
                )
            }
            Button(
                onClick = { batazbesteko=batazbestekoNota (arrayOf(notak[0][2],notak[1][2],
                    notak [2][2]))}
            ) {
                Text( text = "Kalkulatu batazbesteko nota")
            }
            Row() {
                Text(text = "Batazbesteko Nota: ")
                Text(text = "$batazbesteko")

            }
        }
    }
}

fun kalkulatuNota (pos :Int, notak : Array<Array<Int>>):String{
    notak[pos][2]=(notak[pos][0]+notak[pos][1])/2
    return "${notak[pos][2]}"
}

fun batazbestekoNota(notak: Array<Int>):Double{
    var min : Int = notak[0]
    var max : Int = notak[0]
    var batazbesteko : Int = 0
    for (nota in notak){
        if (nota < min){
            min = nota
        } else if (nota > max){
            max = nota
        }
        batazbesteko += nota
    }
    batazbesteko = batazbesteko - max - min
    return (batazbesteko/(notak.size-2)).toDouble()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NotakAPPTheme {
        NotakKalkulatu()
    }
}
// kaixo mundua