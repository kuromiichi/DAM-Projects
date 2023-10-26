package dev.kuromiichi.datos_entre_actividades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.kuromiichi.datos_entre_actividades.databinding.ActivityMainBinding

const val EXTRA_NOMBRE = "nombre"
const val EXTRA_EDAD = "edad"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bActivity2.setOnClickListener {
            val nombre = binding.etNombre.text.toString()
            val edad = binding.etEdad.text.toString()

            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra(EXTRA_NOMBRE, nombre)
                putExtra(EXTRA_EDAD, edad.toInt())
            }

            startActivity(intent)
        }
    }
}
