package com.example.apcpoints

object GerenciarDeLocais {
    private val listaDeLocais = mutableListOf<Local>()

    init {
        // Adicionando alguns locais de exemplo para popular o app inicialmente
        adicionarLocal(Local("Restaurante Central", "Rua das Flores, 123", "Restaurante", 4.5))
        adicionarLocal(Local("Parque da Cidade", "Av. Principal, s/n", "Lazer", 4.8))
        adicionarLocal(Local("Museu de Arte", "Praça da Cultura, 10", "Cultura", 4.2))
        adicionarLocal(Local("Biblioteca Municipal", "Rua da Educação, 45", "Educação", 4.7))
    }

    fun adicionarLocal(local: Local) {
        listaDeLocais.add(local)
    }

    fun getTodosLocais(): List<Local> {
        return listaDeLocais.toList()
    }

    fun buscarPorCategoria(categoria: String): List<Local> {
        return listaDeLocais.filter { it.getCategoria().equals(categoria, ignoreCase = true) }
    }

    fun buscarPorNome(nome: String): List<Local> {
        return listaDeLocais.filter { it.getNome().contains(nome, ignoreCase = true) }
    }

    fun removerLocal(nome: String): Boolean {
        return listaDeLocais.removeIf { it.getNome().equals(nome, ignoreCase = true) }
    }
}
