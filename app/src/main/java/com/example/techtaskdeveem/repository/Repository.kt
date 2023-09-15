package com.example.techtaskdeveem.repository

import com.example.techtaskdeveem.data.remote.ApiService
import com.example.techtaskdeveem.repository.base.BaseRepository
import javax.inject.Inject

class Repository @Inject constructor(private val api: ApiService) : BaseRepository() {

    fun getCategory() = doRequest {
        api.getCategory()
    }

    fun getProductsByCategory(category: String) = doRequest {
        api.getProductsByCategory(category)
    }

    fun getProducts(id: Int?) = doRequest {
        api.getProducts(id)
    }
}