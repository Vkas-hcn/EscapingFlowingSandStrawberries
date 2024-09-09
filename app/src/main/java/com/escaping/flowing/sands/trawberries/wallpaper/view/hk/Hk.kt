package com.escaping.flowing.sands.trawberries.wallpaper.view.hk

import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.escaping.flowing.sands.trawberries.wallpaper.R
import com.escaping.flowing.sands.trawberries.wallpaper.databinding.ActivityHkBinding
import com.escaping.flowing.sands.trawberries.wallpaper.utils.AppShow
import com.escaping.flowing.sands.trawberries.wallpaper.yyy.Yy
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import com.escaping.flowing.sands.trawberries.wallpaper.utils.DownloadImage

class Hk : AppCompatActivity() {
    private val binding by lazy { ActivityHkBinding.inflate(layoutInflater) }
    private lateinit var hkAdapter: HkAdapter

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
        clickFUn()
    }

    private fun clickFUn() {
        binding.imageView.setOnClickListener {
            finish()
        }
        binding.imgSave.setOnClickListener {
            if (DownloadImage.hasWritePermission(this)) {
                saveBitMapToG()
            } else {
                DownloadImage.requestWritePermission(this)
            }
        }
    }

    private fun saveBitMapToG() {
        if (binding.llSaveImg.isVisible) {
            val bitmap = Bitmap.createBitmap(
                binding.llSaveImg.width,
                binding.llSaveImg.height,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            binding.llSaveImg.draw(canvas)
            DownloadImage.bitMapSaveImage(this, bitmap)
        } else {
            val bitmap = Bitmap.createBitmap(
                binding.bgImg.width,
                binding.bgImg.height,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            binding.bgImg.draw(canvas)
            DownloadImage.bitMapSaveImage(this, bitmap)
        }

    }

    private fun initData() {
        val mBitmap = MediaStore.Images.Media.getBitmap(contentResolver, Yy.selectedImageUri)
        binding.imgMeagess.setImageBitmap(mBitmap)
        binding.llSaveImg.isVisible = false
        binding.bgImg.setImageBitmap(mBitmap)
        binding.rvHk.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        hkAdapter = HkAdapter(AppShow.hkDataList, this)
        binding.rvHk.adapter = hkAdapter
        hkAdapter.setOnItemClickListener(object : HkAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                if (position == 0) {
                    binding.llSaveImg.isVisible = false
                    binding.bgImg.isVisible = true
                } else {
                    binding.llSaveImg.isVisible = true
                    binding.bgImg.isVisible = false
                    binding.imgHk.setImageResource(AppShow.hkDataList[position])
                }
            }
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == DownloadImage.REQUEST_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                saveBitMapToG()
            } else {
                DownloadImage.showPermissionDeniedDialog(this)
            }
        }
    }
}