package com.escaping.flowing.sands.trawberries.wallpaper.utils


import android.Manifest
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.escaping.flowing.sands.trawberries.wallpaper.yyy.Yy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DownloadImage {

    val REQUEST_PERMISSION_CODE = 123
    private fun isSdkGreaterThan13(): Boolean {
        return android.os.Build.VERSION.SDK_INT >= 33
    }
    fun hasWritePermission(context: Context): Boolean {
        return (ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED || isSdkGreaterThan13())
    }

    fun requestWritePermission(activity: AppCompatActivity) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            REQUEST_PERMISSION_CODE
        )
    }


    fun showPermissionDeniedDialog(activity: AppCompatActivity) {
        AlertDialog.Builder(activity)
            .setMessage("Storage permission is required to save images to the gallery, please go to the settings page to grant permission.")
            .setPositiveButton("Go to settings") { _, _ ->
                openAppSettings(activity)
            }
            .setNegativeButton("Cancel") { _, _ ->
                Toast.makeText(
                    activity,
                    "Unable to save picture without storage permission.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .show()
    }

    private fun openAppSettings(activity: AppCompatActivity) {
        val intent = Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.data = Uri.parse("package:${activity.packageName}")
        activity.startActivity(intent)
    }

    fun downloadAndSaveImage(activity: AppCompatActivity, imageDrawableId: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            val imageDrawable = ContextCompat.getDrawable(activity, imageDrawableId)
            if (imageDrawable is BitmapDrawable) {
                val bitmap = imageDrawable.bitmap
                val savedUri =
                    saveImageToGallery(bitmap, "MyImage", activity)
                if (savedUri != null) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            activity,
                            "Image saved to gallery。",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            activity,
                            "Failed to save image.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    fun bitMapSaveImage(activity: AppCompatActivity, bitmap: Bitmap) {
        GlobalScope.launch(Dispatchers.IO) {
            val savedUri = saveImageToGallery(bitmap, "MyImage", activity)
            if (savedUri != null) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        activity,
                        "Image saved to gallery。",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        activity,
                        "Failed to save image.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun saveImageToGallery(
        bitmap: Bitmap,
        description: String,
        activity: AppCompatActivity
    ): Uri? {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val fileName = "IMG_$timeStamp.jpg"
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.DESCRIPTION, description)
        }

        val contentResolver: ContentResolver = activity.contentResolver
        val imageUri = contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValues
        )

        imageUri?.let {
            val outputStream: OutputStream? = contentResolver.openOutputStream(it)
            outputStream?.use { stream ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                return imageUri
            }
        }
        return null
    }
}
