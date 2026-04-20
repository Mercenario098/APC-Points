package com.example.apcpoints

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                MainScreen(
                    onPerfilClick = { abrirTela(telaPerfil::class.java) },
                    onBuscarClick = { abrirTela(telaBuscar::class.java) },
                    onLocaisClick = { abrirTela(telaLocais::class.java) },
                    onAvaliacoesClick = { abrirTela(telaAvaliacoes::class.java) }
                )
            }
        }
    }

    private fun abrirTela(cls: Class<*>) {
        startActivity(Intent(this, cls))
    }
}

@Composable
fun MainScreen(
    onPerfilClick: () -> Unit,
    onBuscarClick: () -> Unit,
    onLocaisClick: () -> Unit,
    onAvaliacoesClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Imagem de Fundo
        Image(
            painter = painterResource(id = R.drawable.plano),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Overlay gradiente para melhorar contraste
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.3f))
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Botão Perfil no topo direito
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = onPerfilClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.perfil),
                        contentDescription = "Perfil",
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Logo
            Image(
                painter = painterResource(id = R.drawable.apcpoints),
                contentDescription = "Logo",
                modifier = Modifier.size(200.dp)
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Botões do Menu
            MenuButton(text = "Buscar", icon = R.drawable.buscar, onClick = onBuscarClick)
            Spacer(modifier = Modifier.height(16.dp))
            MenuButton(text = "Locais", icon = R.drawable.locais, onClick = onLocaisClick)
            Spacer(modifier = Modifier.height(16.dp))
            MenuButton(text = "Avaliações", icon = R.drawable.avaliacao, onClick = onAvaliacoesClick)
        }
    }
}

@Composable
fun MenuButton(text: String, icon: Int, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .padding(horizontal = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xCC1E88E5) // Azul semi-transparente
        )
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = Color.White
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White
        )
    }
}
