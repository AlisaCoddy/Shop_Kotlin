package com.example.myapplicationshop

import Product
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.jvm.java

class SecondActivity : AppCompatActivity() {
    private val products = listOf(
        Product(1, "Кольцо 1", 50.0, "Описание 1", R.drawable.one),
        Product(2, "Кольцо 2", 25.5, "Описание 2", R.drawable.two),
        Product(3, "Цепочка 1", 40.0, "Описание 3", R.drawable.three),
        Product(4, "Цепочка 2", 70.5, "Описание 4", R.drawable.four),
        Product(5, "Браслет", 60.0, "Описание 5", R.drawable.five)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        val container = findViewById<LinearLayout>(R.id.catalogContainer)

        products.forEach { product ->
            val view = layoutInflater.inflate(R.layout.item_product, container, false)

            view.findViewById<ImageView>(R.id.ivProductImage).setImageResource(product.ImageRes)
            view.findViewById<TextView>(R.id.tvProductName).text = product.name
            view.findViewById<TextView>(R.id.tvProductPrice).text = "${product.price} $"

            view.findViewById<Button>(R.id.btnDetails).setOnClickListener {
                val intent = Intent(this, DetailActivity::class.java).apply {
                    putExtra("name", product.name)
                    putExtra("price", product.price)
                    putExtra("ImageRes", product.ImageRes)
                    putExtra("description", product.description)
                }
                startActivity(intent)
            }


            container.addView(view)

        }
    }
}