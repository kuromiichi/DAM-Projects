package dev.kuromiichi.linksycompartir

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.kuromiichi.linksycompartir.databinding.ActivityLinksBinding

class LinksActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLinksBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLinksBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageButtonYoutube.setOnClickListener {
            Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/")).apply {
                startActivity(this)
            }
        }

        binding.imageButtonGmail.setOnClickListener {
            Intent(Intent.ACTION_VIEW, Uri.parse("https://mail.google.com/")).apply {
                startActivity(this)
            }
        }
    }
}
