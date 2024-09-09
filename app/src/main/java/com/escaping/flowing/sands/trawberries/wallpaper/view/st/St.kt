package com.escaping.flowing.sands.trawberries.wallpaper.view.st

import android.os.Bundle
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.escaping.flowing.sands.trawberries.wallpaper.R
import com.escaping.flowing.sands.trawberries.wallpaper.databinding.ActivityStBinding
import com.escaping.flowing.sands.trawberries.wallpaper.utils.AppShow.navigateTo
import com.escaping.flowing.sands.trawberries.wallpaper.view.mn.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class St : AppCompatActivity() {
    private val binding by lazy { ActivityStBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initData()
        onBackPressedDispatcher.addCallback(this) {
        }
    }

    private fun initData() {
        lifecycleScope.launch {
            for (i in 1..100) {
                delay(20)
                binding.pbStart.progress = i
            }
            navigateTo<MainActivity>(true)
        }
    }
}