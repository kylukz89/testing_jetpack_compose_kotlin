package com.example.myapplication.ui.theme.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Planetas(val nome: String, val descricao: String, val Imagem: Int) : Parcelable

