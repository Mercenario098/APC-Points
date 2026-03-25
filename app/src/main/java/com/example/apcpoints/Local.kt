package com.example.apcpoints

import java.io.Serializable

class Local(
    private val nome: String,
    private val endereco: String,
    private val categoria: String,
    private val avaliacaoMedia: Double = 0.0,
    private val imagemResId: Int? = null
) : Serializable {
    fun getNome(): String = nome
    fun getEndereco(): String = endereco
    fun getCategoria(): String = categoria
    fun getAvaliacaoMedia(): Double = avaliacaoMedia
    fun getImagemResId(): Int? = imagemResId
}
