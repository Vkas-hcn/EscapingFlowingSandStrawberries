package com.escaping.flowing.sands.trawberries.wallpaper.view.hk

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.escaping.flowing.sands.trawberries.wallpaper.R
import com.escaping.flowing.sands.trawberries.wallpaper.utils.AppShow
import com.escaping.flowing.sands.trawberries.wallpaper.utils.AppShow.navigateTo
import com.escaping.flowing.sands.trawberries.wallpaper.view.dt.Dt
import com.escaping.flowing.sands.trawberries.wallpaper.yyy.Yy

class HkAdapter(private var vpnList: List<Int>, private var activity: Hk) :
    RecyclerView.Adapter<HkAdapter.AppViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private var selectedPosition = 0
    private var listener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    class AppViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgHk: AppCompatImageView = view.findViewById(R.id.img_hk)
        val imgCenter: AppCompatImageView = view.findViewById(R.id.img_center)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_h, parent, false)
        return AppViewHolder(view)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(
        holder: AppViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        val item = vpnList[position]
        holder.imgHk.setImageResource(item)
        holder.imgHk.background = null
        if (position == 0) {
            holder.imgCenter.isVisible = true
            holder.imgCenter.setImageResource(R.drawable.icon_no)
            holder.imgHk.background = activity.resources.getDrawable(R.drawable.bg_item_hk)
        } else {
            holder.imgCenter.isVisible = false
        }
        if (position == selectedPosition) {
            if (position == 0) {
                holder.imgCenter.isVisible = true
                holder.imgCenter.setImageResource(R.drawable.icon_no)
                holder.imgHk.background = activity.resources.getDrawable(R.drawable.bg_item_hk)
            } else {
                holder.imgCenter.isVisible = true
                holder.imgCenter.setImageResource(R.drawable.icon_status)
                holder.imgHk.background = activity.resources.getDrawable(R.drawable.bg_item_hk)
            }
        } else if(position != 0) {
            holder.imgCenter.isVisible = false
            holder.imgHk.background = null
        }else{
            holder.imgHk.background = null
        }
        holder.imgHk.setOnClickListener {
            selectedPosition = position
            notifyDataSetChanged()
            listener?.onItemClick(position)
        }
    }

    override fun getItemCount(): Int = vpnList.size


}
