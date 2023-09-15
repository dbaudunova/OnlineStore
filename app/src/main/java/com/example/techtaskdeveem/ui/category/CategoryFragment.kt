package com.example.techtaskdeveem.ui.category

import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.techtaskdeveem.R
import com.example.techtaskdeveem.core.ui.BaseFragment
import com.example.techtaskdeveem.databinding.FragmentCategoryBinding
import com.example.techtaskdeveem.ui.category.adapter.CategoryAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentCategoryBinding, CategoryViewModel>() {

    private val categoryAdapter = CategoryAdapter(this::onClick)

    private fun onClick(product: String) {
        findNavController().navigate(R.id.oneCategoryFragment, bundleOf(KEY_FOR_BUNDLE to product))
    }

    override fun setupObservers() {
        super.setupObservers()
        viewModel.getCategory.collectUIState(
            state = {},
            onSuccess = {
                categoryAdapter.submitList(it)

            }
        )
    }

    override fun inflateViewBinding(): FragmentCategoryBinding {
        return FragmentCategoryBinding.inflate(layoutInflater)
    }

    override val viewModel: CategoryViewModel by lazy {
        ViewModelProvider(this)[CategoryViewModel::class.java]
    }


    override fun initViewModel() {
        super.initViewModel()
        viewModel.getCategory()
    }

    override fun initViews() {
        super.initViews()
        binding.rvCategory.adapter = categoryAdapter
    }

    companion object {
        const val KEY_FOR_BUNDLE = "KEY_FOR_BUNDLE"
    }
}