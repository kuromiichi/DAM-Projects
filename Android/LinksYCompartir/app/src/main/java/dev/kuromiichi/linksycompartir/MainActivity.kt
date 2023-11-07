package dev.kuromiichi.linksycompartir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.kuromiichi.linksycompartir.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLinks.setOnClickListener {
            Intent(this, LinksActivity::class.java).apply {
                startActivity(this)
            }
        }

        binding.buttonCompartir.setOnClickListener {
            Intent(this, CompartirActivity::class.java).apply {
                startActivity(this)
            }
        }
    }
}
