package com.example.myapplicationshop

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import Product
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import org.w3c.dom.Text

class SecondActivity : AppCompatActivity() {

       private val products = listOf(
           Product( 1, "Cospley1",   18000.50,    "Описание 1",   R.drawable.one),
           Product(2, "Cospley2",   16000.50,  "Описание 2",   R.drawable.two),
           Product(3, "Cospley3",   15000.50,   "Описание 3",   R.drawable.three),
           Product(4,  "Cospley4",   30000.50,  "Описание 4",   R.drawable.four),
       )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        val container = findViewById<LinearLayout>(R.id.catalog)

        products.forEach { product ->
            val view = layoutInflater.inflate(R.layout.Item_product, container, false)

            view.findViewById<ImageView>(R.id.ivProductImage).setImageResource(product.ImageRes)
            view.findViewById<TextView>(R.id.tvProductname).text = product.name
            view.findViewById<TextView>(R.id.tvProductPrice).text = "${product.prise} ₽"
            container.addView(view )
        }
    }
}