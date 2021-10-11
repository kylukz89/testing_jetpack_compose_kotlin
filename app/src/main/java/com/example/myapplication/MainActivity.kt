package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink

import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.models.Planetas
import com.example.myapplication.ui.theme.telaDetalhes

class MainActivity : ComponentActivity() {

    // Deveria ter vindo do retrofit
    val planetas = listOf<Planetas>(
        Planetas( "Terra 1", "Nosso planeta padrão.", R.drawable.planeta_terra),
        Planetas("Terra 2", "Nosso planeta padrão...", R.drawable.planeta_terra)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    // Para navegação entre telas passando argumentos
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "telaListagem") {

                        composable("telaListagem") {
                            telaInicial(navController, planetas = planetas)
                        }

                        composable(
                            "telaDetalhesPlaneta/{index}",
                            // Fetching the argument which has been passed
                            arguments = listOf(navArgument("index") { type = NavType.StringType })
                        ) {
                            navController.currentBackStackEntry?.arguments?.getString("index")?.let { it1 ->
                                telaDetalhes(navController,
                                    it1
                                )
                            }
                        }
                    }
                }
            }
        }
    } 
}



@Composable
fun telaInicial(navController: NavHostController, planetas: List<Planetas>){
    // Equivalente cardview inflada em recyclerview antiga
    LazyColumn() {
        itemsIndexed(planetas) {
            index, item -> cardPlaneta(navController, index, item)
        }
    }
}

@Composable
fun cardPlaneta(navController: NavHostController, index: Int, planeta: Planetas) {
   // CardView
   Card(elevation = 4.dp,
   shape = RoundedCornerShape(15.dp),
   border = BorderStroke(2.dp, color = Color(0x77f5f5f5)),
       modifier = Modifier
           .fillMaxWidth()
           .padding(5.dp)
           .height(120.dp)
           .clickable {
               // Evento click no botão e abre tela de detalhes...



               val dest = "telaDetalhesPlaneta/" + index
               navController.navigate(dest)
           }
   ) {
       // Conteúdo dentro deste cardview
       Row(modifier = Modifier
           .fillMaxSize()
           .padding(15.dp)) {
           Image(
               modifier = Modifier
                   .height(100.dp)
                   .width(100.dp),
               painter = painterResource(id = planeta.Imagem), contentDescription = ""
           )
           Text(
               modifier = Modifier
                   .fillMaxWidth()
                   .height(50.dp)
                   .padding(5.dp),
               text = planeta.nome,
               style = TextStyle(fontWeight = FontWeight.Bold),
               textAlign = TextAlign.Center)

       }

//       Row(modifier = Modifier
//           .fillMaxSize()
//           .padding(15.dp)
//           ) {
//           Image(painterResource(id = planeta.Imagem), contentDescription = "")
//
//           Text(
//               modifier = Modifier
//                   .fillMaxWidth()
//                   .height(50.dp)
//                   .padding(5.dp),
//               text = planeta.descricao,
//               style = TextStyle(fontWeight = FontWeight.Bold),
//               textAlign = TextAlign.Center)
//       }
   } 
}


/*
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
            val vA = valorA.toDouble()
            val vB = valorB.toDouble()
            val resultado = vA + vB
            valorResultado = resultado
        }) {
            Text(text = "Somar")
        }
        Text("Resultado: " + valorResultado)
    }
}
*/
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