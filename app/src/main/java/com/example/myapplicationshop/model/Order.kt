package com.example.myapplicationshop.model

import Product

data class Order(
    val product:Product,

    val dateTime: String,

    // кол-во
    val quantity: Int,

    val totalPrise: Double

)
