package com.example.myapplication.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.MainActivity
import com.example.myapplication.ui.theme.models.Planetas

// Conteúdo dentro deste cardview
@Composable
fun telaDetalhes(navController: NavHostController, planeta: String) {

    val mainPlanetas = MainActivity()

    val alturaRow: Int = 150

    // Coluna para centralizar o contéudo desapartado do botão
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(alturaRow.dp)
                .padding(15.dp)
        ) {
            Image(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
                    .clip(shape = CircleShape),
                painter = painterResource(id = mainPlanetas.planetas[planeta.toInt()].Imagem), contentDescription = ""
            )

            // Titulo e descricao do planeta
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(5.dp),
                    text = mainPlanetas.planetas[planeta.toInt()].nome,
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(5.dp),
                    text = mainPlanetas.planetas[planeta.toInt()].descricao,
                    textAlign = TextAlign.Center
                )
            }
        }

        // Botão de voltar
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            onClick = {
                // Retorna tela
                navController.popBackStack()

            }) {
            Text(text = "Voltar")
        }
    }




}