package com.example.apcpoints

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class telaBuscar : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                BuscarScreen(
                    onBackClick = { finish() },
                    onLocalClick = { local ->
                        val intent = Intent(this, telaAvaliacoes::class.java)
                        intent.putExtra("LOCAL_ESCOLHIDO", local)
                        startActivity(intent)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuscarScreen(onBackClick: () -> Unit, onLocalClick: (Local) -> Unit) {
    var query by remember { mutableStateOf("") }
    var categoriaSelecionada by remember { mutableStateOf("Todos") }
    
    val categorias = listOf("Todos", "Lazer", "Restaurante", "Pizzaria", "Comércio", "Cultura", "Educação")

    val locaisFiltrados = remember(query, categoriaSelecionada) {
        if (categoriaSelecionada == "Todos") {
            if (query.isEmpty()) {
                GerenciarDeLocais.getTodosLocais()
            } else {
                val porNome = GerenciarDeLocais.buscarPorNome(query)
                val porCategoria = GerenciarDeLocais.buscarPorCategoria(query)
                (porNome + porCategoria).distinct()
            }
        } else {
            GerenciarDeLocais.buscarPorCategoria(categoriaSelecionada)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Buscar Locais", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = { 
                    query = it
                    if (it.isNotEmpty()) categoriaSelecionada = "Todos"
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                placeholder = { Text("O que você procura?") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                shape = MaterialTheme.shapes.medium,
                singleLine = true
            )

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categorias) { categoria ->
                    FilterChip(
                        selected = categoriaSelecionada == categoria,
                        onClick = { 
                            categoriaSelecionada = categoria
                            if (categoria != "Todos") query = ""
                        },
                        label = { Text(categoria) }
                    )
                }
            }

            if (locaisFiltrados.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Nenhum local encontrado.", color = Color.Gray)
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(locaisFiltrados) { local ->
                        LocalItem(local, onClick = { onLocalClick(local) })
                    }
                }
            }
        }
    }
}
