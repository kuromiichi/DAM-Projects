package dev.kuromiichi.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import dev.kuromiichi.login.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onCreateSpinnerLenguajes()

        binding.radioGroupTipoUsuario.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.radioProfesor -> Toast.makeText(this, "Se ha elegido Profesor", Toast.LENGTH_SHORT).show()
                R.id.radioAlumno -> Toast.makeText(this, "Se ha elegido Alumno", Toast.LENGTH_SHORT).show()
                R.id.radioInvitado -> Toast.makeText(this, "Se ha elegido Invitado", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun onCreateSpinnerLenguajes() {
        val languages = arrayOf("Java", "Kotlin", "Python", "C#")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, languages)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCursos.adapter = adapter

        binding.spinnerCursos.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(this@RegisterActivity, languages[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented")
            }

        }
    }

    private fun onCreateSpinnerResources() {
        val languages = resources.getStringArray(R.array.lenguajes)
        ArrayAdapter.createFromResource(this, R.array.lenguajes, android.R.layout.simple_spinner_dropdown_item)
            .also {
                it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinnerCursos.adapter = it
            }
    }
}
