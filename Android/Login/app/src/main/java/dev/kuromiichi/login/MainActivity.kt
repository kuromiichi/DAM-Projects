package dev.kuromiichi.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dev.kuromiichi.login.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val USERNAME = "username"
    private val PASSWORD = "password"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener {
            login()
        }

        binding.buttonRegister.setOnClickListener {
            register()
        }
    }

    private fun login() {
        val username = binding.editTextUsername.text.toString()
        val password = binding.editTextPassword.text.toString()

        when {
            username.isEmpty() -> {
                Toast.makeText(this, "Introduzca un nombre de usuario", Toast.LENGTH_SHORT).show()
            }

            password.isEmpty() -> {
                Toast.makeText(this, "Introduzca una contraseña", Toast.LENGTH_SHORT).show()
            }

            username == USERNAME && password == PASSWORD -> {
                Toast.makeText(this, "Bienvenide $username", Toast.LENGTH_SHORT).show()
            }

            else -> {
                Toast.makeText(this, "Nombre de usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun register() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}
