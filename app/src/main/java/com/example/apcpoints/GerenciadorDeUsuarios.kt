package com.example.apcpoints

object GerenciadorDeUsuarios {
    private val listaDeUsuarios = mutableListOf<Usuario>()
    private var usuarioLogado: Usuario? = null

    fun cadastrarUsuario(usuario: Usuario): Boolean {
        if (buscarUsuarioPorEmail(usuario.getEmail()) != null) {
            return false // Usuário já existe
        }
        listaDeUsuarios.add(usuario)
        return true
    }

    fun buscarUsuarioPorEmail(email: String): Usuario? {
        return listaDeUsuarios.find { it.getEmail() == email }
    }

    fun validarLogin(email: String, senha: String): Usuario? {
        val usuario = buscarUsuarioPorEmail(email)
        if (usuario != null && usuario.getSenha() == senha) {
            usuarioLogado = usuario
            return usuario
        }
        return null
    }

    fun getUsuarioLogado(): Usuario? {
        return usuarioLogado
    }

    fun deslogar() {
        usuarioLogado = null
    }

    fun getTodosUsuarios(): List<Usuario> {
        return listaDeUsuarios.toList()
    }

    fun adicionarPontosAoLogado(quantidade: Int) {
        usuarioLogado?.adicionarPontos(quantidade)
    }
}
