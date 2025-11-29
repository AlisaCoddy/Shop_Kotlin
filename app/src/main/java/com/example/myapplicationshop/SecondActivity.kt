package com.example.myapplicationshop

import Product
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView

import androidx.activity.enableEdgeToEdge

import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.KeyPosition
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import kotlin.jvm.java


class SecondActivity : AppCompatActivity() {
    private val products = listOf(
        Product(1, "cosplay 1", 33.60, "Описание 1", R.drawable.one),
        Product(2, "cosplay 2 ", 140.5, "Описание 2", R.drawable.two),
        Product(3, "cosplay 3", 40.0, "Описание 3", R.drawable.three),
        Product(4, "cosplay 4 ", 70.5, "Описание 4", R.drawable.four),
        Product(5, "cosplay 5", 120.0, "Описание 5", R.drawable.five)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        val container = findViewById<ListView>(R.id.lvCatalog)

        val adapter = ProductAdapter(this,  products )
        container.adapter = adapter




//        val container = findViewById<LinearLayout>(R.id.catalog)
//
//        products.forEach { product ->
//            val view = layoutInflater.inflate(R.layout.item_product, container, false)
//
//            view.findViewById<ImageView>(R.id.ivProductImage).setImageResource(product.ImageRes)
//            view.findViewById<TextView>(R.id.tvProductname).text = product.name
//            view.findViewById<TextView>(R.id.tvProductPrice).text = "${product.prise} $"
//
//            view.findViewById<Button>(R.id.btnDetails).setOnClickListener {
//                val intent = Intent(this, DetailActivity::class.java).apply {
//                    putExtra("name", product.name)
//                    putExtra("price", product.prise)
//                    putExtra("ImageRes", product.ImageRes)
//                    putExtra("description", product.descriptor)
//                }
//                startActivity(intent)
//            }
//
//
//            container.addView(view)
//
//        }
    }

}
class ProductAdapter(
    private val context:android.content.Context,
    private val products: List<Product>
    ) : android.widget.BaseAdapter(){

    override fun getCount() = products.size
    override fun getItem(position: Int) = products[position]
    override fun getItemId(position: Int) = position.toLong()
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view =  convertView ?: LayoutInflater.from(context)
             .inflate(R.layout.item_product, parent , false)
        val product = getItem(position)
//Находим элементы в карточке товара
        val image =  view.findViewById<ImageView>(R.id.ivProductImage)
        val name = view.findViewById<TextView>(R.id.tvProductname)
        val button = view.findViewById<Button>(R.id.btnDetails)
        val prise =   view.findViewById<TextView>(R.id.tvProductPrice)

        image.setImageResource(product.ImageRes)
        name.text = product.name
        prise.text = "${product.prise} $"

        button.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java).apply {
//Нажатие на кнопку
                putExtra("name", product.name)
                putExtra("price", product.prise)
                putExtra("ImageRes", product.ImageRes)
                putExtra("description", product.descriptor)
                }
                context.startActivity(intent)
        }

        return view

    }
    }

