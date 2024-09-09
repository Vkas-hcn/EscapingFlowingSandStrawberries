package com.escaping.flowing.sands.trawberries.wallpaper.view.mn

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.escaping.flowing.sands.trawberries.wallpaper.R
import com.escaping.flowing.sands.trawberries.wallpaper.databinding.ActivityMainBinding
import com.escaping.flowing.sands.trawberries.wallpaper.utils.AppShow
import com.escaping.flowing.sands.trawberries.wallpaper.utils.AppShow.navigateTo
import com.escaping.flowing.sands.trawberries.wallpaper.view.hk.Hk
import com.escaping.flowing.sands.trawberries.wallpaper.view.set.Sz
import com.escaping.flowing.sands.trawberries.wallpaper.yyy.Yy

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var mainAdapter: MainAdapter
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
        clickInit()
    }

    private fun initData(){
        binding.rvWall.layoutManager = GridLayoutManager(this,3)
        mainAdapter = MainAdapter(AppShow.bzDataList,this)
        binding.rvWall.adapter = mainAdapter
    }

    private fun clickInit(){
        binding.imgBack.setOnClickListener {
            navigateTo<Sz>()
        }
        binding.tvWall.setOnClickListener {
            binding.showPhoto = false
        }
        binding.tvPhoframe.setOnClickListener {
            binding.showPhoto = true
        }
        binding.imgAdd.setOnClickListener {
            pickImage { selectedImageUri ->
                if (selectedImageUri != null) {
                    Yy.selectedImageUri = selectedImageUri
                    navigateTo<Hk>()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 10056 && resultCode == Activity.RESULT_OK) {
            val selectedImageUri = data?.data
            imagePickerCallback?.invoke(selectedImageUri)
        }
    }

    private var imagePickerCallback: ((Uri?) -> Unit)? = null

    private fun pickImage(callback: (Uri?) -> Unit) {
        imagePickerCallback = callback
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, 10056)
    }

}