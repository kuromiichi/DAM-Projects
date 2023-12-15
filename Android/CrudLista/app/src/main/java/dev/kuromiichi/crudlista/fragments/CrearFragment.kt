package dev.kuromiichi.crudlista.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import dev.kuromiichi.crudlista.Elemento
import dev.kuromiichi.crudlista.Lista
import dev.kuromiichi.crudlista.R
import dev.kuromiichi.crudlista.databinding.FragmentCrearBinding

class CrearFragment : Fragment() {
    private lateinit var binding: FragmentCrearBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCrearBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonCrear.setOnClickListener {
            crearElemento()
        }
    }

    private fun crearElemento() {
        if (!validarElemento()) return
        Lista.lista.add(Elemento(
            binding.nombre.editText!!.text.toString(),
            binding.cbBoolean.isChecked,
            binding.textInt.editText!!.text.toString().toIntOrNull() ?: 0,
            binding.url.editText!!.toString()
        ))
        Lista.mAdapter.notifyItemInserted(Lista.lista.size)
        Toast.makeText(requireContext(), "Elemento creado", Toast.LENGTH_SHORT).show()
    }

    private fun validarElemento(): Boolean {
        if (binding.nombre.toString().isEmpty()) return false
        if (binding.url.toString().isEmpty()) return false
        return true
    }
}
