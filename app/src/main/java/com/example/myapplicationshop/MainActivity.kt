package com.example.myapplicationshop

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import android.widget.Button
import android.widget.Toast
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btn_Start =findViewById<Button>(R.id.btnStart)

        btn_Start.setOnClickListener {
     //       Toast.makeText(this, "Кнопка нажата", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
//
//        btn_Start.setOnLongClickListener {
//            Toast.makeText(this, "Долгое нажатие", Toast.LENGTH_SHORT).show()
//            true
//        }

        }
    }
