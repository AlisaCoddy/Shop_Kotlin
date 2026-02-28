package com.example.myapplicationshop

import Product
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationshop.ProductGridAdapter.VH

class CartAdapter(
    private val items: List<Product>
    ):RecyclerView.Adapter<CartAdapter.VH>() {

    class VH(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById<ImageView>(R.id.ivProductImage)
        val name: TextView = view.findViewById<TextView>(R.id.tvProductname)
        val price: TextView  = view.findViewById<TextView>(R.id.tvProductPrice)
        val button: Button = view.findViewById<Button>(R.id.btnDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.VH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return VH(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: CartAdapter.VH, position: Int) {
        val product = items[position]

        holder.image.setImageResource(product.ImageRes)
        holder.name.text = product.name
        holder.price.text = "${product.prise} $"

        holder.button.visibility = View.GONE

    }

}