package dev.kuromiichi.datos_entre_actividades

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.kuromiichi.datos_entre_actividades.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nombre = intent.getStringExtra(EXTRA_NOMBRE) ?: "Nombre"
        val edad = intent.getIntExtra(EXTRA_EDAD, 0)

        val string = "Hola $nombre. Tienes $edad años."
        binding.tvNombreEdad.text = string
    }
}
