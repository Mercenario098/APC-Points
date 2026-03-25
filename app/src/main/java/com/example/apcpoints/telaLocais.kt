package com.example.apcpoints

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apcpoints.databinding.ActivityTelaLocaisBinding

class telaLocais : AppCompatActivity() {

    private lateinit var binding: ActivityTelaLocaisBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        binding = ActivityTelaLocaisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        configurarRecyclerView()
    }

    private fun configurarRecyclerView() {
        // Configura o layout da lista (um item abaixo do outro)
        binding.recyclerViewLocais.layoutManager = LinearLayoutManager(this)
        
        // Pega a lista de locais do Gerenciador e passa para o Adapter
        val listaDeLocais = GerenciarDeLocais.getTodosLocais()
        val adapter = LocalAdapter(listaDeLocais)
        
        // Define o adapter no RecyclerView
        binding.recyclerViewLocais.adapter = adapter
    }
}
