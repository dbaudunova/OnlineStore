package com.example.techtaskdeveem.data.remote

import com.example.techtaskdeveem.data.remote.model.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("products/categories")
    suspend fun getCategory(): List<String>

    @GET("products/category/{categoryId}")
    suspend fun getProductsByCategory(
        @Path("categoryId") category: String
    ): List<Product>

    @GET("products/{id}")
    suspend fun getProducts(
        @Path("id") id: Int?
    ): Product
}