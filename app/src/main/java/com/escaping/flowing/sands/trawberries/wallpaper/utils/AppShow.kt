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
        R.drawable.new_2,
        R.drawable.new_3,
        R.drawable.new_4,
        R.drawable.new_5,
        R.drawable.bz_02,
        R.drawable.new_6,
        R.drawable.new_7,
        R.drawable.new_8,
        R.drawable.new_9,
        R.drawable.bz_03,
        R.drawable.new_10,
        R.drawable.new_11,
        R.drawable.bz_10,
        R.drawable.new_36,
        R.drawable.new_37,
        R.drawable.new_40,
        R.drawable.new_41,
        R.drawable.bz_14,
        R.drawable.new_42,
        R.drawable.new_43,
        R.drawable.bz_15,
        R.drawable.new_44,
        R.drawable.new_46,
        R.drawable.bz_16,
        R.drawable.new_47,
        R.drawable.new_48,
        R.drawable.bz_17,
        R.drawable.bz_12,
        R.drawable.new_38,
        R.drawable.new_39,
        R.drawable.bz_13,
        R.drawable.new_33,
        R.drawable.new_34,
        R.drawable.new_35,
        R.drawable.bz_11,
        R.drawable.new_30,
        R.drawable.new_31,
        R.drawable.new_32,
        R.drawable.bz_18,
        R.drawable.new_28,
        R.drawable.new_29,
        R.drawable.bz_09,
        R.drawable.new_25,
        R.drawable.bz_08,
        R.drawable.new_26,
        R.drawable.new_27,
        R.drawable.bz_19,
        R.drawable.bz_07,
        R.drawable.new_22,
        R.drawable.new_23,
        R.drawable.new_24,
        R.drawable.bz_20,
        R.drawable.new_18,
        R.drawable.bz_06,
        R.drawable.new_19,
        R.drawable.new_20,
        R.drawable.new_21,
        R.drawable.bz_21,
        R.drawable.new_15,
        R.drawable.new_16,
        R.drawable.bz_05,
        R.drawable.new_17,
        R.drawable.bz_22,
        R.drawable.new_12,
        R.drawable.bz_04,
        R.drawable.new_13,
        R.drawable.new_14,
        R.drawable.bz_23,
        R.drawable.bz_24,
        R.drawable.new_1,
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