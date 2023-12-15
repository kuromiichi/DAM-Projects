package dev.kuromiichi.crudlista.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import dev.kuromiichi.crudlista.*
import dev.kuromiichi.crudlista.Lista
import dev.kuromiichi.crudlista.databinding.FragmentListaBinding

class ListaFragment : Fragment(), ElementoOnClickListener {
    private lateinit var binding: FragmentListaBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()

        binding.addButton.setOnClickListener {
            setCrearFragment()
        }
    }

    private fun setRecyclerView() {
        Lista.mAdapter = ElementoAdapter(Lista.lista, this)
        binding.recyclerView.apply {
            adapter = Lista.mAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setCrearFragment() {
        requireActivity().supportFragmentManager.commit {
            add<CrearFragment>(R.id.fragmentContainer)
            setReorderingAllowed(true)
            addToBackStack("crear")
        }
    }

    override fun onClick(elemento: Elemento) {
        return
    }

    override fun onLongClick(elemento: Elemento): Boolean {
        return false
    }
}
