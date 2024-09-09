package com.escaping.flowing.sands.trawberries.wallpaper.utils

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.escaping.flowing.sands.trawberries.wallpaper.R

object AppShow {
    inline fun <reified T : AppCompatActivity> AppCompatActivity.navigateTo(
        finishCurrent: Boolean = false,
        extras: Intent.() -> Unit = {}
    ) {
        val intent = Intent(this, T::class.java)
        intent.extras()
        startActivity(intent)
        if (finishCurrent) {
            finish()
        }
    }

    val bzDataList = listOf(
        R.drawable.bz_01,
        R.drawable.bz_02,
        R.drawable.bz_03,
        R.drawable.bz_04,
        R.drawable.bz_05,
        R.drawable.bz_06,
        R.drawable.bz_07,
        R.drawable.bz_08,
        R.drawable.bz_09,
        R.drawable.bz_10,
        R.drawable.bz_11,
        R.drawable.bz_12,
        R.drawable.bz_13,
        R.drawable.bz_14,
        R.drawable.bz_15,
        R.drawable.bz_16,
        R.drawable.bz_17,
        R.drawable.bz_18,
        R.drawable.bz_19,
        R.drawable.bz_20,
        R.drawable.bz_21,
        R.drawable.bz_22,
        R.drawable.bz_23,
        R.drawable.bz_24,
    )

    val hkDataList = listOf(
        R.drawable.bg_item_no,
        R.drawable.hk_1,
        R.drawable.hk_2,
        R.drawable.hk_3,
        R.drawable.hk_4,
        R.drawable.hk_5,
        R.drawable.hk_6,
        R.drawable.hk_7,
        R.drawable.hk_8,
        R.drawable.hk_9,
        R.drawable.hk_10,
        R.drawable.hk_11,
        R.drawable.hk_12,
    )

}