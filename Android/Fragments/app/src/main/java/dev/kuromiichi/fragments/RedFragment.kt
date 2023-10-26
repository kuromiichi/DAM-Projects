package dev.kuromiichi.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import dev.kuromiichi.fragments.databinding.FragmentRedBinding

class RedFragment : Fragment(), DatoOnClickListener {
    private lateinit var binding: FragmentRedBinding
    private lateinit var mAdapter: DatoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRedBinding.inflate(inflater, container, false)
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
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onClick(dato: Dato) {
        TODO("Not yet implemented")
    }
}
