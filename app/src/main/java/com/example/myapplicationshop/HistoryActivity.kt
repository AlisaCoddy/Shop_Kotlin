package com.example.myapplicationshop

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationshop.model.HistoryStorage

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_history)
        // находими элементы на экране
        val rv = findViewById<RecyclerView>(R.id.rvHistory)

        //2)  берем список товаров из истории
        val items = HistoryStorage.all()

        //3) Настройка RV
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = HistoryAdapter(items)

    }
}