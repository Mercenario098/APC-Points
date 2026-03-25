package com.example.apcpoints

class Usuario(
    private var nome: String,
    private val email: String,
    private var senha: String,
    private var pontos: Int = 0,
    private val gostosMusicais: MutableList<String> = mutableListOf(),
    private val categoriasFavoritas: MutableList<String> = mutableListOf()
) {
    fun getNome(): String = nome
    fun getEmail(): String = email
    fun getSenha(): String = senha
    fun getPontos(): Int = pontos
    
    fun getGostosMusicais(): List<String> = gostosMusicais
    fun getCategoriasFavoritas(): List<String> = categoriasFavoritas

    fun adicionarPontos(valor: Int) {
        this.pontos += valor
    }

    fun adicionarGostoMusical(gosto: String) {
        if (!gostosMusicais.contains(gosto)) gostosMusicais.add(gosto)
    }

    fun adicionarCategoriaFavorita(categoria: String) {
        if (!categoriasFavoritas.contains(categoria)) categoriasFavoritas.add(categoria)
    }

    fun setNome(nome: String) {
        this.nome = nome
    }

    fun setSenha(senha: String) {
        this.senha = senha
    }
}
