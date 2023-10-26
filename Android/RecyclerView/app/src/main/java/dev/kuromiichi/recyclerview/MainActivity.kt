package dev.kuromiichi.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import dev.kuromiichi.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), DatoOnClickListener {
    private lateinit var binding: ActivityMainBinding

    private lateinit var mAdapter: DatoAdapter
    private lateinit var mLayoutManager: GridLayoutManager

    private val listaDato = (1..10).map {
        Dato("Dato $it", "https://loremflickr.com/320/240/cat/?lock=$it")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRecyclerView()
    }

    private fun setRecyclerView() {
        mAdapter = DatoAdapter(listaDato, this)
        mLayoutManager = GridLayoutManager(this, 3)

        binding.recyclerView.apply {
            layoutManager = mLayoutManager
            adapter = mAdapter
        }
    }

    override fun onClick(dato: Dato) {
        Toast.makeText(this, dato.nombre, Toast.LENGTH_SHORT).show()
    }
}
