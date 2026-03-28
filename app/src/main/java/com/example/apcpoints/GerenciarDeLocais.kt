package com.example.apcpoints

object GerenciarDeLocais {
    private val listaDeLocais = mutableListOf<Local>()

    init {
        // Adicionando alguns locais de exemplo para popular o app inicialmente
        adicionarLocal(
            Local(
                "Praça Três Poderes",
                "Avenida Tancredo Neves",
                "Lazer",
                4.8,
                listOf(
                    R.drawable.praca_3_poderes_2
                )
            )
        )
        
        adicionarLocal(
            Local(
                "Rio do Povoado São Miguel",
                "Povoado São Miguel",
                "Lazer",
                4.9,
                listOf(R.drawable.rio_do_povoado_sao_miguel)
            )
        )

        adicionarLocal(
            Local(
                "Praia do Barão",
                "Beira Mar",
                "Lazer",
                4.5,
                listOf(R.drawable.praia_do_barao1)
            )
        )

        adicionarLocal(
            Local (
                "Praia de Baleia",
                "Beira Mar",
                "Lazer",
                4.5,
                listOf(R.drawable.praia_de_baleia)
            )
        )


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
