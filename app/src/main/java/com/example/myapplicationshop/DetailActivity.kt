package com.example.myapplicationshop

import Product
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplicationshop.model.CartStorage

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
        val productId = intent.getIntExtra("id", -1) // -1 = не пришло

        detailImage.setImageResource(productImageRes)
        detailName.text = productName
        detailPrice.text = "$productPrice $"
        detailDescription.text = productDescription

        buyButton.setOnClickListener {
            // собирает обект класса Product из отдельных переменных, это товар который мы добавляем
            val one_new_product = Product(id =productId, name = productName, prise = productPrice, descriptor = productDescription,
                ImageRes =  productImageRes)
            //  добавление нового товара в корзину(своя функция)
            CartStorage.add_item(this,one_new_product)
            // сообщение об успешном добавлении
            Toast.makeText(this, "Товар добавлен в корзину", Toast.LENGTH_SHORT).show()
        }
        backButton.setOnClickListener {
            finish()
        }

    }

}