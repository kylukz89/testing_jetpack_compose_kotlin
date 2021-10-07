package com.example.myapplication

import android.content.ClipData
import android.os.Bundle
import android.preference.PreferenceActivity
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    telaInicial()
                }
            }
        }
    }
}


@Composable
fun telaInicial() {

    var valorA by remember {
        mutableStateOf("")
    }
    var valorB by remember {
        mutableStateOf("")
    }
    var valorResultado by remember {
        mutableStateOf(0.0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(label = { Text(text = "Digite o valor 1") },
            value = valorA.toString(),
            onValueChange = {
                valorA = it
            })
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(label = { Text(text = "Digite o valor 2") },
            value = valorB.toString(),
            onValueChange = {
                valorB = it
            })

        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            var vA = valorA.toDouble()
            var vB = valorB.toDouble()
            var resultado = vA+vB
            valorResultado = resultado
        }) {
            Text(text = "Somar")
        }
        Text("Resultado: " + valorResultado)
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}