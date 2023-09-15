package com.example.techtaskdeveem.ui.detail_info

import com.example.techtaskdeveem.core.ui.BaseViewModel
import com.example.techtaskdeveem.data.remote.model.Product
import com.example.techtaskdeveem.repository.Repository
import com.example.techtaskdeveem.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DetailInfoViewModel @Inject constructor(private val repository: Repository): BaseViewModel() {

    private val _getProduct = MutableStateFlow<UIState<Product>>(UIState.Empty())
    val getProducts: StateFlow<UIState<Product>> = _getProduct


    fun getProducts(id:Int?){
        repository.getProducts(id).collectData(_getProduct)
    }
}