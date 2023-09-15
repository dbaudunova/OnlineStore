package com.example.techtaskdeveem.data.remote.model

import java.io.Serializable


data class Product(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String
) : Serializable {
    data class Rating(
        val count: Int,
        val rate: Double
    ) : Serializable
}