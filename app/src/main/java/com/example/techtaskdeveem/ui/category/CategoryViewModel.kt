package com.example.techtaskdeveem.ui.category

import com.example.techtaskdeveem.core.ui.BaseViewModel
import com.example.techtaskdeveem.repository.Repository
import com.example.techtaskdeveem.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {

    private val _getCategory = MutableStateFlow<UIState<List<String>>>(UIState.Empty())
    val getCategory: StateFlow<UIState<List<String>>> = _getCategory


    fun getCategory() {
        repository.getCategory().collectDataList(_getCategory)
    }
}