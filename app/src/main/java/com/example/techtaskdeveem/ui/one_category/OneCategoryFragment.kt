package com.example.techtaskdeveem.ui.one_category

import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.techtaskdeveem.R
import com.example.techtaskdeveem.core.ui.BaseFragment
import com.example.techtaskdeveem.data.remote.model.Product
import com.example.techtaskdeveem.databinding.FragmentOneCategoryBinding
import com.example.techtaskdeveem.ui.adapter.ProductAdapter
import com.example.techtaskdeveem.ui.category.CategoryFragment.Companion.KEY_FOR_BUNDLE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OneCategoryFragment : BaseFragment<FragmentOneCategoryBinding, OneCategoryViewModel>() {

    private val category = "jewelery"
    private var product: String? = null

    private val adapter by lazy {
        ProductAdapter(this::onClick)
    }

    private fun onClick(product: Product) {
        findNavController().navigate(
            R.id.detailInfoFragment,
            bundleOf(KEY_FOR_DETAIL_INFO to product)
        )
    }

    private fun onCategoryClick(product: String) {
        viewModel.changeCategories(product)
        viewModel.changeCategories.observe(viewLifecycleOwner) {
            viewModel.getOneCategory(it)
        }
    }

    override fun setupObservers() {
        super.setupObservers()
        viewModel.getOneCategory.collectUIState(
            state = {},
            onSuccess = { adapter.submitList(it as MutableList<Product>) }
        )
    }

    override fun initViewModel() {
        super.initViewModel()
        onCategoryClick(product.toString())
    }

    override fun initViews() {
        super.initViews()
        product = (arguments?.getSerializable(KEY_FOR_BUNDLE) as String)
        binding.rvProduct.adapter = adapter
    }

    override val viewModel: OneCategoryViewModel by lazy {
        ViewModelProvider(this)[OneCategoryViewModel::class.java]
    }

    override fun inflateViewBinding(): FragmentOneCategoryBinding {
        return FragmentOneCategoryBinding.inflate(layoutInflater)
    }

    companion object {
        const val KEY_FOR_DETAIL_INFO = "KEY_FOR_DETAIL_INFO"
    }
}