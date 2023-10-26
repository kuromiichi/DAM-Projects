package dev.kuromiichi.dialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import dev.kuromiichi.dialog.databinding.ActivityMainBinding
import dev.kuromiichi.dialog.databinding.DialogDatoBinding

class MainActivity : AppCompatActivity(), DatoOnClickListener {
    private lateinit var binding: ActivityMainBinding

    private lateinit var mAdapter: DatoAdapter
    private lateinit var mLayoutManager: GridLayoutManager

    private val listaDato = mutableListOf<Dato>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRecyclerView()

        binding.addButton.setOnClickListener {
            showInputDialog()
        }
    }

    private fun setRecyclerView() {
        mAdapter = DatoAdapter(listaDato, this)
        mLayoutManager = GridLayoutManager(this, 1)

        binding.recyclerView.apply {
            adapter = mAdapter
            layoutManager = mLayoutManager
        }
    }

    private fun showInputDialog() {
        val bindingDato = DialogDatoBinding.inflate(layoutInflater)

        val dialog = AlertDialog.Builder(this)
        dialog.setView(bindingDato.root)
        dialog.setPositiveButton("Añadir") { _, _ ->
            val dato = Dato(bindingDato.name.text.toString())
            listaDato.add(dato)
            mAdapter.notifyItemInserted(listaDato.size)
        }

        dialog.apply {
            create()
            show()
        }
    }

    override fun onClick(dato: Dato) {
        Toast.makeText(this, dato.name, Toast.LENGTH_SHORT).show()
    }
}
