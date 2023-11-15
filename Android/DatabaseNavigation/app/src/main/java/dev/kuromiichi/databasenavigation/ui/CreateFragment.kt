package dev.kuromiichi.databasenavigation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import dev.kuromiichi.databasenavigation.database.NotaDatabase
import dev.kuromiichi.databasenavigation.databinding.FragmentCreateBinding
import dev.kuromiichi.databasenavigation.entity.Nota

class CreateFragment : Fragment() {
    private lateinit var mBinding: FragmentCreateBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentCreateBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setButton()
    }

    private fun setButton() {
        mBinding.buttonSave.setOnClickListener {
            if (
                mBinding.title.editText!!.text.toString().isNotEmpty() &&
                mBinding.content.editText!!.text.toString().isNotEmpty()
            ) {
                Thread {
                    println("INSERT A LA BASE DE DATOS")
                    NotaDatabase.getDb(requireContext()).notaDao().insert(
                        Nota(
                            title = mBinding.title.editText!!.text.toString(),
                            content = mBinding.content.editText!!.text.toString(),
                            image = mBinding.image.editText!!.text.toString()
                                .ifEmpty { "https://icons.veryicon.com/png/o/miscellaneous/basic-icon-1/unknown-18.png" }
                        )
                    )
                }.apply {
                    start()
                    join()
                }

                Toast.makeText(requireContext(), "Nota guardada", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
