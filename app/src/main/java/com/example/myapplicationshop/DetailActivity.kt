package com.example.myapplicationshop

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        //      Получение данных из intent
        val productName = intent.getStringExtra("name") ?: "Товар"
        val productPrice = intent.getDoubleExtra("price", 0.0)
        val productImageRes = intent.getIntExtra("ImageRes", 0)
        val productDescription = intent.getStringExtra("description") ?: "Описание отсутствует"

//      Находим view на экране
        val detailImage = findViewById<ImageView>(R.id.detailImage)
        val detailName = findViewById<TextView>(R.id.detailName)
        val detailPrice = findViewById<TextView>(R.id.detailPrice)
        val detailDescription = findViewById<TextView>(R.id.detailDescription)
        val buyButton = findViewById<Button>(R.id.buyButton)
        val backButton = findViewById<Button>(R.id.backButton)

        detailImage.setImageResource(productImageRes)
        detailName.text = productName
        detailPrice.text = "$productPrice $"
        detailDescription.text = productDescription
    }
}