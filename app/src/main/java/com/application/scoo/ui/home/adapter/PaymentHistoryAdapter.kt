package com.application.scoo.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.application.scoo.R
import com.application.scoo.data.model.Datum
import java.text.SimpleDateFormat
import java.util.*


class PaymentHistoryAdapter(private val paymentHistory: List<Datum?>?)
    : RecyclerView.Adapter<PaymentHistoryAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflate the custom view from xml layout file
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_payment_history, parent,false)

        // return the view holder
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var date = paymentHistory?.get(position)!!.paidDate!!.dropLast(14)
        var spf = SimpleDateFormat("yyyy-MM-dd")
        val newDate: Date = spf.parse(date)
        spf = SimpleDateFormat("dd/MM/yyyy")
        date = spf.format(newDate)
        println(date)

        holder.date.text = date
        holder.amount.text = paymentHistory[position]!!.paidAmount.toString()
        holder.paymentType.text = paymentHistory[position]!!.paymentmethos
    }


    override fun getItemCount(): Int {
        // number of items in the data set held by the adapter
        return paymentHistory!!.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val date: TextView = itemView.findViewById(R.id.TV_date)
        val amount: TextView = itemView.findViewById(R.id.TV_amount)
        val paymentType: TextView = itemView.findViewById(R.id.TV_payment_type)
    }


    // this two methods useful for avoiding duplicate item
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun getItemViewType(position: Int): Int {
        return position
    }
}