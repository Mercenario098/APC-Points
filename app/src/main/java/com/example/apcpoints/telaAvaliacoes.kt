package com.example.apcpoints

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apcpoints.databinding.ActivityTelaAvaliacoesBinding

class telaAvaliacoes : AppCompatActivity() {

    private lateinit var binding: ActivityTelaAvaliacoesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        binding = ActivityTelaAvaliacoesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Recebe o local que foi clicado na lista (pode ser nulo se vier da tela principal)
        val local = intent.getSerializableExtra("LOCAL_ESCOLHIDO") as? Local
        
        if (local != null) {
            exibirDadosDoLocal(local)
            configurarAvaliacoes(local)
        } else {
            exibirTodasAvaliacoes()
        }
    }

    private fun exibirDadosDoLocal(local: Local) {
        binding.textNomeLocalDetalhe.text = local.getNome()
        binding.textEnderecoLocalDetalhe.text = local.getEndereco()
        
        local.getImagemResId()?.let {
            binding.imgLocalDetalhe.setImageResource(it)
        }
    }

    private fun exibirTodasAvaliacoes() {
        binding.textNomeLocalDetalhe.text = "Todas as Avaliações"
        binding.textEnderecoLocalDetalhe.text = "Mural da Comunidade"
        
        // Esconde os campos de avaliação já que não há um local específico selecionado
        binding.ratingBarVotar.visibility = View.GONE
        binding.btnEnviarAvaliacao.visibility = View.GONE
        
        // Tenta esconder o campo de texto (TextInputLayout)
        (binding.editComentario.parent.parent as? View)?.visibility = View.GONE

        // Configura o RecyclerView para mostrar tudo o que existe no app
        binding.recyclerComentarios.layoutManager = LinearLayoutManager(this)
        val todasAvaliacoes = GerenciadorDeAvaliacoes.getTodasAvaliacoes()
        binding.recyclerComentarios.adapter = AvaliacaoAdapter(todasAvaliacoes)
    }

    private fun configurarAvaliacoes(local: Local) {
        binding.recyclerComentarios.layoutManager = LinearLayoutManager(this)
        atualizarListaDeComentarios(local.getNome())

        binding.btnEnviarAvaliacao.setOnClickListener {
            val nota = binding.ratingBarVotar.rating
            val comentario = binding.editComentario.text.toString()
            val usuarioLogado = GerenciadorDeUsuarios.getUsuarioLogado()

            if (usuarioLogado == null) {
                Toast.makeText(this, "Você precisa estar logado para avaliar!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (comentario.isNotEmpty()) {
                val novaAvaliacao = Avaliacao(local.getNome(), usuarioLogado.getEmail(), nota, comentario)
                GerenciadorDeAvaliacoes.adicionarAvaliacao(novaAvaliacao)
                
                binding.editComentario.text?.clear()
                binding.ratingBarVotar.rating = 0f
                atualizarListaDeComentarios(local.getNome())
                
                Toast.makeText(this, "Avaliação enviada com sucesso!", Toast.LENGTH_SHORT).show()
                GerenciadorDeUsuarios.adicionarPontosAoLogado(5)
            } else {
                Toast.makeText(this, "Por favor, escreva um comentário.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun atualizarListaDeComentarios(nomeLocal: String) {
        val listaComentarios = GerenciadorDeAvaliacoes.getAvaliacoesDoLocal(nomeLocal)
        binding.recyclerComentarios.adapter = AvaliacaoAdapter(listaComentarios)
    }
}
