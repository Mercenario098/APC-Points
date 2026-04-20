package com.example.apcpoints

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class telaAvaliacoes : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val local = intent.getSerializableExtra("LOCAL_ESCOLHIDO") as? Local

        setContent {
            MaterialTheme {
                AvaliacoesScreen(
                    local = local,
                    onBackClick = { finish() },
                    onEnviarAvaliacao = { nota, comentario ->
                        val usuarioLogado = GerenciadorDeUsuarios.getUsuarioLogado()
                        if (usuarioLogado == null) {
                            Toast.makeText(this, "Você precisa estar logado!", Toast.LENGTH_SHORT).show()
                        } else if (comentario.isEmpty()) {
                            Toast.makeText(this, "Escreva um comentário!", Toast.LENGTH_SHORT).show()
                        } else if (local != null) {
                            val nova = Avaliacao(local.getNome(), usuarioLogado.getEmail(), nota, comentario)
                            GerenciadorDeAvaliacoes.adicionarAvaliacao(nova)
                            GerenciadorDeUsuarios.adicionarPontosAoLogado(5)
                            Toast.makeText(this, "Avaliação enviada!", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AvaliacoesScreen(
    local: Local?,
    onBackClick: () -> Unit,
    onEnviarAvaliacao: (Float, String) -> Unit
) {
    var nota by remember { mutableStateOf(5f) }
    var comentario by remember { mutableStateOf("") }
    
    // Lista de avaliações reativa
    val listaAvaliacoes = if (local != null) {
        GerenciadorDeAvaliacoes.getAvaliacoesDoLocal(local.getNome())
    } else {
        GerenciadorDeAvaliacoes.getTodasAvaliacoes()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(local?.getNome() ?: "Avaliações", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Cabeçalho com dados do Local
            if (local != null) {
                item {
                    LocalHeader(local)
                }

                // Formulário de Avaliação
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Sua Nota:", fontWeight = FontWeight.Bold)
                            Slider(
                                value = nota,
                                onValueChange = { nota = it },
                                valueRange = 1f..5f,
                                steps = 3
                            )
                            Text("Nota selecionada: ${nota.toInt()}", fontSize = 12.sp)
                            
                            OutlinedTextField(
                                value = comentario,
                                onValueChange = { comentario = it },
                                label = { Text("Escreva um comentário...") },
                                modifier = Modifier.fillMaxWidth(),
                                minLines = 3
                            )
                            
                            Button(
                                onClick = { 
                                    onEnviarAvaliacao(nota, comentario)
                                    comentario = "" // Limpa o campo
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp)
                            ) {
                                Text("Enviar Avaliação")
                            }
                        }
                    }
                }
            }

            item {
                Text(
                    text = "Comentários",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            // Lista de Comentários
            items(listaAvaliacoes) { avaliacao ->
                AvaliacaoItemCompose(avaliacao)
            }
        }
    }
}

@Composable
fun LocalHeader(local: Local) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = local.getImagemPrincipal() ?: R.drawable.apcpoints),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(local.getNome(), fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E88E5))
            Text(local.getEndereco(), fontSize = 14.sp, color = Color.Gray)
        }
    }
}

@Composable
fun AvaliacaoItemCompose(avaliacao: Avaliacao) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(avaliacao.getUsuarioEmail(), fontWeight = FontWeight.Bold, color = Color(0xFF1E88E5), fontSize = 14.sp)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFFFC107), modifier = Modifier.size(16.dp))
                    Text(text = avaliacao.getNota().toInt().toString(), fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(avaliacao.getComentario(), fontSize = 15.sp)
        }
    }
}
