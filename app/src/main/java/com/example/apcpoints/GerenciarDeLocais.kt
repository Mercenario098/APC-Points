package com.example.apcpoints

object GerenciarDeLocais {
    private val listaDeLocais = mutableListOf<Local>()

    init {
        popularLazer()
        popularRestaurantes()
        popularPizzarias()
        popularComercio()
        popularCultura()
        popularEducacao()
    }

    private fun popularLazer() {
        val lazer = listOf(
            Local("Rio do Povoado São Miguel",
                "Povoado São Miguel",
                "Lazer", 4.9,
                listOf(R.drawable.rio_do_povoado_sao_miguel)),

            Local("Praia do Barão",
                "Beira Mar",
                "Lazer",
                4.5,
                listOf(R.drawable.praia_do_barao1)),

            Local("Praia de Baleia",
                "Beira Mar",
                "Lazer",
                4.5,
                listOf(R.drawable.praia_de_baleia))
        )
        listaDeLocais.addAll(lazer)
    }

    private fun popularRestaurantes() {
        val restaurantes = listOf<Local>(
            Local("Restaurante Central", "Rua Principal, 100", "Restaurante", 4.7, listOf(R.drawable.apcpoints))
        )
        listaDeLocais.addAll(restaurantes)
    }

    private fun popularPizzarias() {
        val pizzarias = listOf<Local>(
            Local("Pizzaria Bella Italia", "Av. das Palmeiras, 50", "Pizzaria", 4.9, listOf(R.drawable.apcpoints))
        )
        listaDeLocais.addAll(pizzarias)
    }

    private fun popularComercio() {
        val comercio = listOf<Local>(
            Local("Supermercado Econômico", "Rua do Comércio, 200", "Comércio", 4.2, listOf(R.drawable.apcpoints))
        )
        listaDeLocais.addAll(comercio)
    }

    private fun popularCultura() {
        val cultura = listOf<Local>(
            Local("Praça Três Poderes",
                "Avenida Tancredo Neves",
                "Cultura",
                4.8,
                listOf(R.drawable.praca_3_poderes_2)),
        )
        listaDeLocais.addAll(cultura)
    }

    private fun popularEducacao() {
        val educacao = listOf<Local>(
            // Adicione escolas/faculdades aqui
        )
        listaDeLocais.addAll(educacao)
    }

    fun adicionarLocal(local: Local) {
        listaDeLocais.add(local)
    }

    fun getTodosLocais(): List<Local> {
        return listaDeLocais.sortedByDescending { it.getAvaliacaoMedia() }
    }

    fun buscarPorCategoria(categoria: String): List<Local> {
        return listaDeLocais
            .filter { it.getCategoria().equals(categoria, ignoreCase = true) }
            .sortedByDescending { it.getAvaliacaoMedia() }
    }

    fun buscarPorNome(nome: String): List<Local> {
        return listaDeLocais.filter { it.getNome().contains(nome, ignoreCase = true) }
    }

    fun removerLocal(nome: String): Boolean {
        return listaDeLocais.removeIf { it.getNome().equals(nome, ignoreCase = true) }
    }
}
