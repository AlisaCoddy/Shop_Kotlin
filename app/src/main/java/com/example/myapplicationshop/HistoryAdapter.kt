package com.example.myapplicationshop

import Product
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationshop.CartAdapter.VH
import com.example.myapplicationshop.model.Order

class HistoryAdapter (
    private val items: List<Order>
    ):RecyclerView.Adapter<HistoryAdapter.VH>(){
        class VH(view: View) : RecyclerView.ViewHolder(view) {
            val image: ImageView = view.findViewById<ImageView>(R.id.ivHistoryImage)
            val name: TextView = view.findViewById<TextView>(R.id.tvHistoryname)
            val price: TextView  = view.findViewById<TextView>(R.id.tvHistoryPrice)
            val date: TextView = view.findViewById<TextView>(R.id.tvHistoryDate)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapter.VH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_history, parent, false)
        return VH(view)
    }
    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: HistoryAdapter.VH, position: Int) {
        val order = items[position]

        holder.image.setImageResource(order.product.ImageRes)
        holder.name.text = order.product.name
        holder.price.text = "${order.quantity} * ${order.product.prise} $ = ${order.totalPrise}$"
        holder.date.text = order.dateTime



    }



}


