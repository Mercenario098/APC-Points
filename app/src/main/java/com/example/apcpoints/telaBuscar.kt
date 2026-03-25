package com.example.apcpoints

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apcpoints.databinding.ActivityTelaBuscarBinding
import com.google.android.material.chip.Chip

class telaBuscar : AppCompatActivity() {

    private lateinit var binding: ActivityTelaBuscarBinding
    private lateinit var adapter: LocalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        binding = ActivityTelaBuscarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        configurarRecyclerView()
        configurarBusca()
        configurarFiltros()
    }

    private fun configurarRecyclerView() {
        binding.recyclerBusca.layoutManager = LinearLayoutManager(this)
        val listaInicial = GerenciarDeLocais.getTodosLocais()
        adapter = LocalAdapter(listaInicial)
        binding.recyclerBusca.adapter = adapter
    }

    private fun configurarBusca() {
        binding.editBusca.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Ao digitar, desmarcamos os filtros de chip para priorizar a busca por texto
                binding.chipGroupFiltros.clearCheck()
                binding.chipTodos.isChecked = s.isNullOrEmpty()
                filtrarLocais(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun configurarFiltros() {
        binding.chipGroupFiltros.setOnCheckedStateChangeListener { group, checkedIds ->
            if (checkedIds.isEmpty()) {
                filtrarLocais("") // Se nada estiver marcado, mostra tudo
                return@setOnCheckedStateChangeListener
            }

            val chipId = checkedIds.first()
            val chip = findViewById<Chip>(chipId)
            val categoriaSelecionada = chip.text.toString()

            if (categoriaSelecionada == "Todos") {
                binding.editBusca.text?.clear()
                filtrarLocais("")
            } else {
                // Quando clica em um filtro, limpa o texto da busca para não confundir
                binding.editBusca.text?.clear()
                val listaFiltrada = GerenciarDeLocais.buscarPorCategoria(categoriaSelecionada)
                atualizarLista(listaFiltrada)
            }
        }
    }

    private fun filtrarLocais(texto: String) {
        val listaFiltrada = if (texto.isEmpty()) {
            GerenciarDeLocais.getTodosLocais()
        } else {
            val porNome = GerenciarDeLocais.buscarPorNome(texto)
            val porCategoria = GerenciarDeLocais.buscarPorCategoria(texto)
            (porNome + porCategoria).distinct()
        }
        atualizarLista(listaFiltrada)
    }

    private fun atualizarLista(lista: List<Local>) {
        adapter = LocalAdapter(lista)
        binding.recyclerBusca.adapter = adapter

        if (lista.isEmpty()) {
            binding.textSemResultados.visibility = View.VISIBLE
        } else {
            binding.textSemResultados.visibility = View.GONE
        }
    }
}
