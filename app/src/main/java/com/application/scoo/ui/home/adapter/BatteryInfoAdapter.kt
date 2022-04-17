package com.application.scoo.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.application.scoo.R


class BatteryInfoAdapter(private val batteryLevel: MutableList<String?>)
    : RecyclerView.Adapter<BatteryInfoAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflate the custom view from xml layout file
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_battery_info, parent,false)

        // return the view holder
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // display the current animal
        holder.batteryLevel.text = batteryLevel[position]
    }


    override fun getItemCount(): Int {
        // number of items in the data set held by the adapter
        return batteryLevel.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val batteryLevel: TextView = itemView.findViewById(R.id.battery_level)
    }


    // this two methods useful for avoiding duplicate item
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun getItemViewType(position: Int): Int {
        return position
    }
}