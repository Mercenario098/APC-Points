package com.example.apcpoints

class Avaliacao(
    private val localNome: String,
    private val usuarioEmail: String,
    private val nota: Float, // Valor de 0 a 5
    private val comentario: String = ""
) {
    fun getLocalNome(): String = localNome
    fun getUsuarioEmail(): String = usuarioEmail
    fun getNota(): Float = nota
    fun getComentario(): String = comentario
}
