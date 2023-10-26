package dev.kuromiichi.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import dev.kuromiichi.fragments.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    val lista = (1..10).map { Dato("Dato $it") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setButtons()
    }

    private fun setButtons() {
        binding.redButton.setOnClickListener {
            setRedFragment()
        }

        binding.greenButton.setOnClickListener {
            setGreenFragment()
        }

        binding.blueButton.setOnClickListener {
            setBlueFragment()
        }
    }

    private fun setRedFragment() {
        supportFragmentManager.commit {
            add<RedFragment>(R.id.fragmentContainerView)
            setReorderingAllowed(true)
            addToBackStack("red")
        }
    }

    private fun setGreenFragment() {
        supportFragmentManager.commit {
            add<GreenFragment>(R.id.fragmentContainerView)
            setReorderingAllowed(true)
            addToBackStack("green")
        }
    }

    private fun setBlueFragment() {
        supportFragmentManager.commit {
            add<BlueFragment>(R.id.fragmentContainerView)
            setReorderingAllowed(true)
            addToBackStack("blue")
        }
    }

}
