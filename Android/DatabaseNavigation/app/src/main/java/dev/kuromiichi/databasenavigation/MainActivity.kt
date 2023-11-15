package dev.kuromiichi.databasenavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.window.OnBackInvokedDispatcher
import androidx.activity.addCallback
import androidx.navigation.findNavController
import dev.kuromiichi.databasenavigation.database.NotaDatabase
import dev.kuromiichi.databasenavigation.databinding.ActivityMainBinding
import dev.kuromiichi.databasenavigation.ui.CreateFragmentDirections
import dev.kuromiichi.databasenavigation.ui.HomeFragmentDirections

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setNavBar()

        onBackPressedDispatcher.addCallback(this) { }

        // Debug
        Thread {
            NotaDatabase.getDb(this).notaDao().getAll().also { println(it) }
        }.start()
    }

    private fun setNavBar() {
        binding.bottomNav.setOnItemSelectedListener {
            if (it.itemId == R.id.action_create && binding.bottomNav.selectedItemId != it.itemId) {
                val action = HomeFragmentDirections.actionHomeToCreate()
                findNavController(binding.fragmentContainer.id).navigate(action)
            } else if (it.itemId == R.id.action_home && binding.bottomNav.selectedItemId != it.itemId) {
                val action = CreateFragmentDirections.actionCreateToHome()
                findNavController(binding.fragmentContainer.id).navigate(action)
            }
            true
        }
    }

    override fun getOnBackInvokedDispatcher(): OnBackInvokedDispatcher {
        return super.getOnBackInvokedDispatcher()
    }
}
