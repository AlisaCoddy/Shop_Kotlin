package com.example.myapplicationshop

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import android.widget.Button
import android.widget.Toast
import android.content.Intent
import android.view.MotionEvent
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplicationshop.model.CartStorage
import com.example.myapplicationshop.model.HistoryStorage

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        CartStorage.init(this)
        HistoryStorage.init(this)

        val btn_Start =findViewById<Button>(R.id.btnStart)

        btn_Start.setOnClickListener {
     //       Toast.makeText(this, "Кнопка нажата", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        btn_Start.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                v.startAnimation(AnimationUtils.loadAnimation(v.context, R.anim.scale_down))
            }

            if (event.action == MotionEvent.ACTION_UP) {
                v.startAnimation(AnimationUtils.loadAnimation(v.context, R.anim.scale_up))
            }

            if (event.action == MotionEvent.ACTION_CANCEL) {
                v.startAnimation(AnimationUtils.loadAnimation(v.context, R.anim.scale_up))
            }
            false
        }
//
//        btn_Start.setOnLongClickListener {
//            Toast.makeText(this, "Долгое нажатие", Toast.LENGTH_SHORT).show()
//            true
//        }

        }
    }
