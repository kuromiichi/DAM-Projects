package dev.kuromiichi.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import dev.kuromiichi.fragments.databinding.FragmentGreenBinding

class GreenFragment : Fragment(), DatoOnClickListener {
    private lateinit var binding: FragmentGreenBinding
    private lateinit var mAdapter: DatoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
    }

    private fun setRecyclerView() {
        mAdapter = DatoAdapter(ListaDato.lista, this)
        binding.recyclerView.apply {
            adapter = mAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    override fun onClick(dato: Dato) {
        TODO("Not yet implemented")
    }

}
