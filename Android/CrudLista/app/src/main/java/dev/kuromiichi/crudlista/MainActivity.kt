package dev.kuromiichi.crudlista

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import dev.kuromiichi.crudlista.databinding.ActivityMainBinding
import dev.kuromiichi.crudlista.fragments.ListaFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListaFragment()
    }

    private fun setListaFragment() {
        supportFragmentManager.commit {
            add<ListaFragment>(R.id.fragmentContainer)
            setReorderingAllowed(true)
            addToBackStack("lista")
        }
    }

}
