package com.example.techtaskdeveem.ui.detail_info

import android.annotation.SuppressLint
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.example.techtaskdeveem.core.ui.BaseFragment
import com.example.techtaskdeveem.data.remote.model.Product
import com.example.techtaskdeveem.databinding.FragmentDetailInfoBinding
import com.example.techtaskdeveem.ui.one_category.OneCategoryFragment.Companion.KEY_FOR_DETAIL_INFO
import com.example.techtaskdeveem.utils.loadImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailInfoFragment : BaseFragment<FragmentDetailInfoBinding, DetailInfoViewModel>() {

    private var product: Product? = null

    override fun initViews() {
        super.initViews()
        product =arguments?.getSerializable(KEY_FOR_DETAIL_INFO) as Product?
    }

    override fun inflateViewBinding(): FragmentDetailInfoBinding {
        return FragmentDetailInfoBinding.inflate(layoutInflater)
    }

    @SuppressLint("SetTextI18n")
    override fun setupObservers() {
        super.setupObservers()
        viewModel.getProducts.collectUIState(
            state = {},
            onSuccess = {
                binding.tvCategory.text = it.category
                binding.tvRating.text = it.rating.rate.toString()
                binding.tvTitle.text = it.title
                binding.ivProductImage.loadImage(it.image)
                binding.tvPrice.text = it.price.toString() + " $"
                binding.tvDescription.text = it.description
            }
        )
    }

    override fun initListener() {
        super.initListener()
        binding.btnShare.setOnClickListener {
                val url = product?.image.toString()
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, url)
                startActivity(Intent.createChooser(intent, "Share via"))
        }
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel.getProducts(product?.id)
    }

    override val viewModel: DetailInfoViewModel by lazy {
        ViewModelProvider(this)[DetailInfoViewModel::class.java]
    }

}