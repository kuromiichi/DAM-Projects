package dev.kuromiichi.databasenavigation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import dev.kuromiichi.databasenavigation.databinding.FragmentHomeBinding
import dev.kuromiichi.databasenavigation.entity.Nota
import dev.kuromiichi.databasenavigation.adapter.NotaAdapter
import dev.kuromiichi.databasenavigation.adapter.NotaOnClickListener
import dev.kuromiichi.databasenavigation.database.NotaDatabase
import dev.kuromiichi.databasenavigation.entity.NotaLista

class HomeFragment : Fragment(), NotaOnClickListener {
    private lateinit var mBinding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setRecyclerView()
    }

    private fun setRecyclerView() {
        println("SETRECYCLERVIEW")
        Thread {
            NotaLista.notas = NotaDatabase.getDb(requireContext()).notaDao().getAll().toMutableList()
        }.apply {
            start()
            join()
        }
        NotaLista.mAdapter = NotaAdapter(NotaLista.notas, this)

        mBinding.recyclerView.apply {
            adapter = NotaLista.mAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onClick(nota: Nota) {
        TODO("Not yet implemented")
    }
}
