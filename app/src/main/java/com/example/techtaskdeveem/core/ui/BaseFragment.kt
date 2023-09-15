package com.example.techtaskdeveem.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.example.techtaskdeveem.utils.UIState
import com.example.techtaskdeveem.utils.showToast
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel> : Fragment() {

    protected lateinit var binding: VB
    protected abstract fun inflateViewBinding(): VB
    protected abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflateViewBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initViewModel()
        initListener()
        setupObservers()
    }

    open fun setupObservers() {}
    open fun initListener() {}
    open fun initViewModel() {}
    open fun initViews() {}

    protected fun <T> StateFlow<UIState<T>>.collectUIState(
        state: (UIState<T>) -> Unit,
        onSuccess: (data: T) -> Unit
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                this@collectUIState.collect { res ->
                    state.invoke(res)
                    when (res) {
                        is UIState.Empty -> {}
                        is UIState.Error -> {
                            showToast(res.message)
                        }

                        is UIState.Loading -> {}

                        is UIState.Success -> {
                            onSuccess(res.data)
                        }
                    }
                }
            }
        }
    }
}