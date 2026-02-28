package com.example.myapplicationshop

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationshop.model.CartStorage


class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cart)

//      1) Находим элементы на экране
        val rv = findViewById<RecyclerView>(R.id.rvCartList)
        val tvTotal = findViewById<TextView>(R.id.tvCartTotalSum)
        val btnClear = findViewById<Button>(R.id.btnClearCart)

//      2) Берем список товаров из корзины
        val items = CartStorage.all()

//      3) Настройка RV
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = CartAdapter(items)

//      4) Считаем сумму
        var total = 0.0
        for (elem in items){
            total += elem.prise
        }
        tvTotal.text = "ИТОГО: ${total} Р"

//      5) Очистка корзины
        btnClear.setOnClickListener {
            CartStorage.clear(this)
            rv.adapter = CartAdapter(emptyList())
            tvTotal.text = "ИТОГО: 0 Р"
        }

    }
}