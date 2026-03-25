package com.example.apcpoints

object GerenciadorDeAvaliacoes {
    private val todasAvaliacoes = mutableListOf<Avaliacao>()

    /*init {
        // Exemplos de comentários para aparecerem na tela
        adicionarAvaliacao(Avaliacao("Restaurante Central", "joao@email.com", 5f, "Comida maravilhosa!"))
        adicionarAvaliacao(Avaliacao("Restaurante Central", "maria@email.com", 4f, "Muito bom, mas demorou um pouco."))
        adicionarAvaliacao(Avaliacao("Parque da Cidade", "pedro@email.com", 5f, "Lugar perfeito para relaxar."))
        adicionarAvaliacao(Avaliacao("Museu de Arte", "ana@email.com", 5f, "Exposição incrível!"))
    }*/

    fun adicionarAvaliacao(avaliacao: Avaliacao) {
        todasAvaliacoes.add(avaliacao)
    }

    fun getAvaliacoesDoLocal(nomeLocal: String): List<Avaliacao> {
        return todasAvaliacoes
            .filter { it.getLocalNome() == nomeLocal }
            .sortedByDescending { it.getNota() }
    }

    fun getTodasAvaliacoes(): List<Avaliacao> {
        // Retorna todas as avaliações do app, ordenadas pelas mais recentes ou melhores notas
        return todasAvaliacoes.sortedByDescending { it.getNota() }
    }
}
