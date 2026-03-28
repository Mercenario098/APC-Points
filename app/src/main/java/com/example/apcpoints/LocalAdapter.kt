package com.example.apcpoints

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LocalAdapter(private val lista: List<Local>) : 
    RecyclerView.Adapter<LocalAdapter.LocalViewHolder>() {

    class LocalViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nome: TextView = view.findViewById(R.id.textNomeLocal)
        val endereco: TextView = view.findViewById(R.id.textEnderecoLocal)
        val categoria: TextView = view.findViewById(R.id.textCategoriaLocal)
        val nota: TextView = view.findViewById(R.id.textNotaLocal)
        val imagem: ImageView = view.findViewById(R.id.imgLocal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocalViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_local, parent, false)
        return LocalViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocalViewHolder, position: Int) {
        val local = lista[position]
        holder.nome.text = local.getNome()
        holder.endereco.text = local.getEndereco()
        holder.categoria.text = local.getCategoria()
        holder.nota.text = String.format("%.1f", local.getAvaliacaoMedia())
        
        // Exibe a primeira imagem da lista (se existir)
        local.getImagemPrincipal()?.let {
            holder.imagem.setImageResource(it)
        } ?: run {
            // Opcional: define uma imagem padrão se não houver imagens
            // holder.imagem.setImageResource(R.drawable.placeholder)
        }

        // Configura o clique no item para abrir a tela de avaliações
        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, telaAvaliacoes::class.java)
            intent.putExtra("LOCAL_ESCOLHIDO", local)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount() = lista.size
}
