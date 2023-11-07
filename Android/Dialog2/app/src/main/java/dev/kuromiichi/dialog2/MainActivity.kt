package dev.kuromiichi.dialog2

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import dev.kuromiichi.dialog2.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupButtons()
    }

    private fun setupButtons() {
        binding.btnAlert.setOnClickListener {
            showAlertDialog()
        }
        binding.btnLista.setOnClickListener {
            showListDialog()
        }
        binding.btnSingleSelection.setOnClickListener {
            showListUnDialog()
        }
        binding.btnMultiSelection.setOnClickListener {
            showVariadaDialog()
        }
        binding.btnFecha.setOnClickListener {
            showFechaDialog()
        }
        binding.btnHora.setOnClickListener {
            showHoraDialog()
        }
    }

    private fun showAlertDialog() {
        AlertDialog.Builder(this).apply {
            setTitle("Alert")
            setMessage("Esto es una alerta")
            setPositiveButton("Ok") { _, _ ->
                Toast.makeText(this@MainActivity, "Ok", Toast.LENGTH_SHORT).show()
            }
            setNegativeButton("Cancel") { _, _ ->
                Toast.makeText(this@MainActivity, "Cancel", Toast.LENGTH_SHORT).show()
            }
            setNeutralButton("Neutral") { _, _ ->
                Toast.makeText(this@MainActivity, "Neutral", Toast.LENGTH_SHORT).show()
            }
        }.show()
    }

    private fun showListDialog() {
        val items = arrayOf("Item 1", "Item 2", "Item 3")

        AlertDialog.Builder(this).apply {
            setTitle("List")
            setItems(items) { _, which ->
                val item = items[which]
                when (item) {
                    "Item 1" -> Toast.makeText(this@MainActivity, "Item 1", Toast.LENGTH_SHORT).show()
                    "Item 2" -> Toast.makeText(this@MainActivity, "Item 2", Toast.LENGTH_SHORT).show()
                    "Item 3" -> Toast.makeText(this@MainActivity, "Item 3", Toast.LENGTH_SHORT).show()
                }
            }
            setNegativeButton("Cancel") { _, _ ->
                Toast.makeText(this@MainActivity, "Cancel", Toast.LENGTH_SHORT).show()
            }
        }.show()
    }

    private fun showListUnDialog() {
        val items = arrayOf("Item 1", "Item 2", "Item 3")

        AlertDialog.Builder(this).apply {
            setTitle("Selección simple")
            setSingleChoiceItems(items, -1) { _, which ->
                val item = items[which]
                Toast.makeText(this@MainActivity, item, Toast.LENGTH_SHORT).show()
            }
            setPositiveButton("Confirm") { _, _ ->
                Toast.makeText(this@MainActivity, "Confirm", Toast.LENGTH_SHORT).show()
            }
            setNegativeButton("Cancel") { _, _ ->
                Toast.makeText(this@MainActivity, "Cancel", Toast.LENGTH_SHORT).show()
            }
        }.show()
    }

    private fun showVariadaDialog() {
        val selectedList = mutableListOf<Int>()
        val items = arrayOf("Item 1", "Item 2", "Item 3")

        AlertDialog.Builder(this).apply {
            setTitle("Selección múltiple")
            setMultiChoiceItems(items, null) { _, which, isChecked ->
                if (isChecked) {
                    selectedList.add(which)
                } else if (selectedList.contains(which)) {
                    selectedList.remove(which)
                }
            }
            setPositiveButton("Confirm") { _, _ ->
                val selectedItems = mutableListOf<String>()
                selectedList.forEach { index -> selectedItems.add(items[index]) }
                Toast.makeText(this@MainActivity, "Selected: ${selectedItems.joinToString()}", Toast.LENGTH_SHORT)
                    .show()
            }
            setNegativeButton("Cancel") { _, _ ->
                Toast.makeText(this@MainActivity, "Cancel", Toast.LENGTH_SHORT).show()
            }
        }.show()
    }

    private fun showFechaDialog() {
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            this,
            { _, year, month, day ->
                calendar.set(year, month, day)
                binding.tvFecha.text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time)
            },
            calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun showHoraDialog() {
        val calendar = Calendar.getInstance()
        TimePickerDialog(
            this,
            { _, hour, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hour)
                calendar.set(Calendar.MINUTE, minute)
                binding.tvHora.text = SimpleDateFormat("HH:mm", Locale.getDefault()).format(calendar.time)
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        ).show()
    }
}
