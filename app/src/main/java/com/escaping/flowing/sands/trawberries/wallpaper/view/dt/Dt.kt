package com.escaping.flowing.sands.trawberries.wallpaper.view.dt

import android.app.WallpaperManager
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.escaping.flowing.sands.trawberries.wallpaper.R
import com.escaping.flowing.sands.trawberries.wallpaper.databinding.ActivityDtBinding
import com.escaping.flowing.sands.trawberries.wallpaper.utils.AppShow
import com.escaping.flowing.sands.trawberries.wallpaper.utils.DownloadImage
import com.escaping.flowing.sands.trawberries.wallpaper.view.mn.MainAdapter
import com.escaping.flowing.sands.trawberries.wallpaper.yyy.Yy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Dt : AppCompatActivity() {
    private val binding by lazy { ActivityDtBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.main.setBackgroundResource(AppShow.bzDataList[Yy.wallPos])
        clickFUn()
    }

    private fun clickFUn() {
        binding.imageView.setOnClickListener {
            finish()
        }
        binding.linLock.setOnClickListener {
            setWallpaperFun(WallpaperManager.FLAG_LOCK)
        }
        binding.linHome.setOnClickListener {
            setWallpaperFun(WallpaperManager.FLAG_SYSTEM)
        }
        binding.linBoth.setOnClickListener {
            setWallpaperBoth()
        }
        binding.llApply.setOnClickListener {
            binding.llDialog.isVisible = true
            binding.llApply.isVisible = false
            binding.imgDown.isVisible = false
        }
        binding.llCancle.setOnClickListener {
            disDialog()
        }
        binding.imgDown.setOnClickListener {
            if (DownloadImage.hasWritePermission(this)) {
                DownloadImage.downloadAndSaveImage(this, AppShow.bzDataList[Yy.wallPos])
            } else {
                DownloadImage.requestWritePermission(this)
            }
        }
    }
    private fun disDialog(){
        binding.llDialog.isVisible = false
        binding.llApply.isVisible = true
        binding.imgDown.isVisible = true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == DownloadImage.REQUEST_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                DownloadImage.downloadAndSaveImage(this, AppShow.bzDataList[Yy.wallPos])
            } else {
                DownloadImage.showPermissionDeniedDialog(this)
            }
        }
    }

    private fun setWallpaperFun(type: Int) {
        lifecycleScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                binding.progressCircular.isVisible = true
            }
            val bitmap: Bitmap =
                BitmapFactory.decodeResource(resources, AppShow.bzDataList[Yy.wallPos])
            val wallpaperManager = WallpaperManager.getInstance(this@Dt)

            try {
                wallpaperManager.setBitmap(
                    bitmap,
                    null,
                    false,
                    type
                )
                withContext(Dispatchers.Main) {
                    disDialog()
                    binding.progressCircular.isVisible = false
                    Toast.makeText(this@Dt, "Set the wallpaper successfully", Toast.LENGTH_SHORT)
                        .show()
                }
            } catch (e: Exception) {
                disDialog()
                withContext(Dispatchers.Main) {
                    binding.progressCircular.isVisible = false
                    Toast.makeText(this@Dt, "Failed to set image", Toast.LENGTH_SHORT).show()
                }
                e.printStackTrace()
            }
        }
    }

    private fun setWallpaperBoth() {
        lifecycleScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                binding.progressCircular.isVisible = true
            }
            val bitmap: Bitmap =
                BitmapFactory.decodeResource(resources, AppShow.bzDataList[Yy.wallPos])
            val wallpaperManager = WallpaperManager.getInstance(this@Dt)
            try {
                wallpaperManager.setBitmap(
                    bitmap,
                    null,
                    false,
                    WallpaperManager.FLAG_LOCK
                )
                wallpaperManager.setBitmap(
                    bitmap,
                    null,
                    false,
                    WallpaperManager.FLAG_SYSTEM
                )
                withContext(Dispatchers.Main) {
                    disDialog()
                    binding.progressCircular.isVisible = false
                    Toast.makeText(this@Dt, "Set the wallpaper successfully", Toast.LENGTH_SHORT)
                        .show()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    disDialog()
                    binding.progressCircular.isVisible = false
                    Toast.makeText(this@Dt, "Failed to set image", Toast.LENGTH_SHORT).show()
                }
                e.printStackTrace()
            }
        }
    }
}