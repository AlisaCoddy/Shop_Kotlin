package com.example.myapplicationshop

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationshop.model.CartStorage
import com.example.myapplicationshop.model.HistoryStorage
import com.example.myapplicationshop.model.Order
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale.getDefault
import java.util.logging.SimpleFormatter


class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cart)

//      1) Находим элементы на экране
        val rv = findViewById<RecyclerView>(R.id.rvCartList)
        val tvTotal = findViewById<TextView>(R.id.tvCartTotalSum)
        val btnClear = findViewById<Button>(R.id.btnClearCart)
        val btnOpenHistory = findViewById<Button>(R.id.btnOpenHistory)
        val btnMakeOrder = findViewById<Button>(R.id.btnMakeOrder)

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
        tvTotal.text = "ИТОГО: ${total} $"

//      5) Очистка корзины
        btnClear.setOnClickListener {
            CartStorage.clear(this)
            rv.adapter = CartAdapter(emptyList())
            tvTotal.text = "ИТОГО: 0 $"
        }

//      6) Переход в историю
        btnOpenHistory.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }

//      7) Оформление заказа
        btnMakeOrder.setOnClickListener {

            // список всех товаров из корзины
            val cartItems = CartStorage.all()

            // если корзина пустая, то ничего не делаем
            if (cartItems.isEmpty()){
                return@setOnClickListener
            }

            // получение даты и времени
            val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm", getDefault())
            val date = formatter.format(Date())

            // пустой список для добавления в историю
            val purchases = mutableListOf<Order>()

            // перебираем товары в корзине
            for (elem in cartItems){
                var found = false // следит за тем есть товар в истории или нет

                // перебираем покупки в истории (индексы)
                for (i in purchases.indices){
                    // если id товара в истории == id товара в корзине
                    if (purchases[i].product.id == elem.id){
                        // увеличиваем quantity, totalPrise
                        val old = purchases[i]
                        purchases[i] = Order(
                            product = old.product,
                            quantity = old.quantity + 1,
                            totalPrise = old.product.prise * (old.quantity + 1),
                            dateTime = old.dateTime
                        )

                        found = true
                        break
                    }
                }

                // если товара еще нет в истории
                if (found == false){
                    purchases.add(Order(
                        product = elem,
                        quantity = 1,
                        totalPrise = elem.prise * 1,
                        dateTime = date
                    ))
                }

            }

            // сохранение в истории (настройках телефона)
            HistoryStorage.add_all(this, purchases)
            // очистка корзины и экрана
            CartStorage.clear(this)
            rv.adapter = CartAdapter(emptyList())
            tvTotal.text = "ИТОГО:0 $"

        }



    }
}