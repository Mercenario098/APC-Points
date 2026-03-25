package com.example.apcpoints

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AvaliacaoAdapter(private val lista: List<Avaliacao>) : 
    RecyclerView.Adapter<AvaliacaoAdapter.AvaliacaoViewHolder>() {

    class AvaliacaoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val email: TextView = view.findViewById(R.id.textUsuarioEmail)
        val nota: RatingBar = view.findViewById(R.id.ratingBarItem)
        val comentario: TextView = view.findViewById(R.id.textComentarioItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvaliacaoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_avaliacao, parent, false)
        return AvaliacaoViewHolder(view)
    }

    override fun onBindViewHolder(holder: AvaliacaoViewHolder, position: Int) {
        val avaliacao = lista[position]
        holder.email.text = avaliacao.getUsuarioEmail()
        holder.nota.rating = avaliacao.getNota()
        holder.comentario.text = avaliacao.getComentario()
    }

    override fun getItemCount() = lista.size
}
