package dev.kuromiichi.recetas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.kuromiichi.recetas.databinding.FragmentRecetasBinding

class RecetasFragment : Fragment() {
    private lateinit var binding: FragmentRecetasBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecetasBinding.inflate(inflater, container, false)
        return binding.root
    }
}
