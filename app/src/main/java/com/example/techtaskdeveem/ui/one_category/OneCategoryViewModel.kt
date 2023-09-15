package com.example.techtaskdeveem.ui.one_category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.techtaskdeveem.core.ui.BaseViewModel
import com.example.techtaskdeveem.data.remote.model.Product
import com.example.techtaskdeveem.repository.Repository
import com.example.techtaskdeveem.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class OneCategoryViewModel @Inject constructor(private val repository: Repository) :
    BaseViewModel() {

    private val _getOneCategory = MutableStateFlow<UIState<List<Product>>>(UIState.Empty())
    val getOneCategory: StateFlow<UIState<List<Product>>> = _getOneCategory

    fun getOneCategory(category: String) {
        repository.getProductsByCategory(category).collectData(_getOneCategory)
    }

    private val _changeCategories = MutableLiveData<String>()
    val changeCategories : LiveData<String> = _changeCategories


    fun changeCategories(category: String){
        _changeCategories.value = category
    }
}