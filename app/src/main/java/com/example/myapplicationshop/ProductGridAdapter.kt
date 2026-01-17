package com.example.myapplicationshop

import Product
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductGridAdapter(
    private val context:android.content.Context,
    private val products: List<Product>
) : RecyclerView.Adapter<ProductGridAdapter.VH>(){
    class VH(view: View) : RecyclerView.ViewHolder(view){
        val image: ImageView= view.findViewById<ImageView>(R.id.ivProductImage)
        val name: TextView= view.findViewById<TextView>(R.id.tvProductname)
        val price: TextView= view.findViewById<TextView>(R.id.btnDetails)
        val button: Button = view.findViewById<Button>(R.id.tvProductPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product_grid,parent, false)
        return VH(view)

    }

    override fun getItemCount() = products.size



    }



}