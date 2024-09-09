package com.escaping.flowing.sands.trawberries.wallpaper.view.mn

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.escaping.flowing.sands.trawberries.wallpaper.R
import com.escaping.flowing.sands.trawberries.wallpaper.utils.AppShow
import com.escaping.flowing.sands.trawberries.wallpaper.utils.AppShow.navigateTo
import com.escaping.flowing.sands.trawberries.wallpaper.view.dt.Dt
import com.escaping.flowing.sands.trawberries.wallpaper.yyy.Yy

class MainAdapter(private var vpnList: List<Int>,private var activity: MainActivity) :
    RecyclerView.Adapter<MainAdapter.AppViewHolder>() {

    class AppViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgWall: AppCompatImageView = view.findViewById(R.id.img_wall)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_z, parent, false)
        return AppViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        val item = vpnList[position]
        Glide
            .with(Yy.app)
            .load(item)
            .thumbnail(0.1f)
            .into(holder.imgWall)
        holder.imgWall.setOnClickListener {
            Yy.wallPos = position
            activity.navigateTo<Dt>()
        }
    }

    override fun getItemCount(): Int = vpnList.size


}
