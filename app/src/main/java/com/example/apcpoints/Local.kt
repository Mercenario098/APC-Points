package com.example.apcpoints

import java.io.Serializable

class Local(
    private val nome: String,
    private val endereco: String,
    private val categoria: String,
    private val avaliacaoMedia: Double = 0.0,
    private val imagens: List<Int> = emptyList()
) : Serializable {
    fun getNome(): String = nome
    fun getEndereco(): String = endereco
    fun getCategoria(): String = categoria
    fun getAvaliacaoMedia(): Double {
        val avaliacoes = GerenciadorDeAvaliacoes.getAvaliacoesDoLocal(nome)
        if (avaliacoes.isEmpty()) return 0.0
        return avaliacoes.map { it.getNota() }.average()
    }
    fun getImagens(): List<Int> = imagens
    fun getImagemPrincipal(): Int? = imagens.firstOrNull()
}
