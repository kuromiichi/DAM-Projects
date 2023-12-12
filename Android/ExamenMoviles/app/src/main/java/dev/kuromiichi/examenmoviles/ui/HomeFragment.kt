package dev.kuromiichi.examenmoviles.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import dev.kuromiichi.examenmoviles.adapters.NotaAdapter
import dev.kuromiichi.examenmoviles.databinding.FragmentHomeBinding
import dev.kuromiichi.examenmoviles.listeners.NotaOnClickListener
import dev.kuromiichi.examenmoviles.models.Nota
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.util.Calendar
import java.util.Date
import java.util.Locale

class HomeFragment : Fragment(), NotaOnClickListener {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
    }

    private fun setRecyclerView() {
        binding.recyclerView.apply {
            adapter = NotaAdapter(mutableListOf(), this@HomeFragment)
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    override fun onClick(nota: Nota) {
        TODO("Not yet implemented")
    }
}