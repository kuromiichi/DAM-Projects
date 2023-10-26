package dev.kuromiichi.test_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dev.kuromiichi.test_app.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonRegister.setOnClickListener {
            showRegisterInfo()
        }
    }

    private fun showRegisterInfo() {
        val user = binding.editTextEmail.text.toString()
        val password = binding.editTextPassword.text.toString()
        Toast
            .makeText(this, "Usuario: $user\nContraseña: $password", Toast.LENGTH_LONG)
            .show()
    }
}
