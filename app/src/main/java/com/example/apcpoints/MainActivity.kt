package com.example.apcpoints

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apcpoints.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnPerfil.setOnClickListener {
            abrirTelaPerfil()
        }


        binding.btnBuscar.setOnClickListener {
            abrirTelaBuscar()
        }


        binding.btnLocais.setOnClickListener {
            abrirTelaLocais()
        }


        binding.btnAvaliacoes.setOnClickListener {
            abrirTelaAvaliacoes()
        }
    }

    private fun abrirTelaPerfil() {
        val intent = Intent(this, telaPerfil::class.java)
        startActivity(intent)
    }

    private fun abrirTelaBuscar() {
        val intent = Intent(this, telaBuscar::class.java)
        startActivity(intent)
    }

    private fun abrirTelaLocais() {
        val intent = Intent(this, telaLocais::class.java)
        startActivity(intent)
    }

    private fun abrirTelaAvaliacoes() {
        val intent = Intent(this, telaAvaliacoes::class.java)
        startActivity(intent)
    }

}
