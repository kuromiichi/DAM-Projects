package dev.kuromiichi.linksycompartir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.kuromiichi.linksycompartir.databinding.ActivityCompartirBinding

class CompartirActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCompartirBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCompartirBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCompartir.setOnClickListener {
            val mensaje = binding.tilCompartir.editText?.text.toString()

            Intent.createChooser(
                Intent(Intent.ACTION_SEND).apply {
                    putExtra(Intent.EXTRA_TEXT, mensaje)
                    type = "text/plain"
                }, null
            ).apply { startActivity(this) }
        }
    }
}
