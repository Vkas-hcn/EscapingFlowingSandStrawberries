package com.escaping.flowing.sands.trawberries.wallpaper.view.set

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.escaping.flowing.sands.trawberries.wallpaper.R
import com.escaping.flowing.sands.trawberries.wallpaper.databinding.ActivityStBinding
import com.escaping.flowing.sands.trawberries.wallpaper.databinding.ActivitySzBinding

class Sz : AppCompatActivity() {
    private val binding by lazy { ActivitySzBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.tvPrivacy.setOnClickListener {
            startActivity(
                Intent(
                    "android.intent.action.VIEW", Uri.parse("https://baidu.com/")
                )
            )
        }
        binding.imageView.setOnClickListener { finish() }
    }
}