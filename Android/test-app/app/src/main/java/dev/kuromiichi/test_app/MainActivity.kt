package dev.kuromiichi.test_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dev.kuromiichi.test_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val USER = "abubi"
    private val PASSWORD = "1234"

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
        val user = binding.editTextUser.text.toString()
        val password = binding.editTextPassword.text.toString()

        if (user == USER && password == PASSWORD) {
            Toast.makeText(this, "Login correcto", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Login incorrecto", Toast.LENGTH_LONG).show()
        }
    }

    private fun register() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}
