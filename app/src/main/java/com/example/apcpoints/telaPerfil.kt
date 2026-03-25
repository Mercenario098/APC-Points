package com.example.apcpoints

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apcpoints.databinding.ActivityTelaPerfilBinding

class telaPerfil : AppCompatActivity() {

    private lateinit var binding: ActivityTelaPerfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        binding = ActivityTelaPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configuração do botão de Entrar (Login)
        binding.buttonEntrar.setOnClickListener {
            val email = binding.textEmailAddress.text.toString()
            val senha = binding.textPassword.text.toString()

            if (email.isNotEmpty() && senha.isNotEmpty()) {
                val usuario = GerenciadorDeUsuarios.validarLogin(email, senha)
                if (usuario != null) {
                    Toast.makeText(this, "Bem-vindo, ${usuario.getNome()}!", Toast.LENGTH_SHORT).show()
                    
                    // Vai para a tela principal (MainActivity) após o login
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish() // Fecha a tela de login/perfil para não voltar nela ao apertar o botão "voltar"
                } else {
                    Toast.makeText(this, "E-mail ou senha incorretos", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }

        // Configuração do botão de Cadastre-se
        binding.buttonCasdastro.setOnClickListener {
            val intent = Intent(this, telaCadastro::class.java)
            startActivity(intent)
        }
    }
}
