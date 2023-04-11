package com.arifwidayana.sell.presentation.ui.preview

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import coil.load
import com.arifwidayana.core.base.BaseFragment
import com.arifwidayana.core.utils.FieldErrorException
import com.arifwidayana.sell.databinding.FragmentPreviewBinding
import com.arifwidayana.sell.presentation.ui.sell.SellViewModel
import com.arifwidayana.shared.utils.Constant
import com.arifwidayana.shared.utils.ext.convertCurrency
import com.arifwidayana.shared.utils.ext.source
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class PreviewFragment : BaseFragment<FragmentPreviewBinding, SellViewModel>(
    FragmentPreviewBinding::inflate
) {
    private val args by navArgs<PreviewFragmentArgs>()
    override val viewModel: SellViewModel by inject()

    override fun initView() {
        onView()
        onClick()
    }

    private fun onView() {
        binding.apply {
            sivImageProduct.load(args.preview.sellParamRequest.image)
            sivPhotoSeller.load(args.preview.userParamResponse.imageUrl)
            tvTitleProduct.text = args.preview.sellParamRequest.name
            tvCategoryProduct.text =
                args.preview.sellParamRequest.categoryId.joinToString { it.name }
            tvPriceProduct.text = convertCurrency(args.preview.sellParamRequest.basePrice)
            tvDescriptionProduct.text = args.preview.sellParamRequest.description
            tvUsernameSeller.text = args.preview.userParamResponse.fullName
            tvLocationSeller.text = args.preview.userParamResponse.city
        }
    }

    private fun onClick() {
        binding.apply {
            ibBack.setOnClickListener {
                moveNavigateUp()
            }
            btnPublish.setOnClickListener {
                viewModel.createProduct(args.preview.sellParamRequest)
            }
        }
    }

    override fun observeData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.createProductResult.collect {
                    it.source(
                        doOnLoading = {},
                        doOnSuccess = {
                            showMessageToast(true, "Create product success!")
                        },
                        doOnError = { error ->
                            when (error.exception) {
                                is FieldErrorException -> handleErrorField(error.exception as FieldErrorException)
                                else -> showMessageSnackBar(true, exception = error.exception)
                            }
                        }
                    )
                }
            }
        }
    }

    private fun handleErrorField(fieldErrorException: FieldErrorException) {
        binding.apply {
            fieldErrorException.errorFields.forEach {
                if (it.first == Constant.SELL_IMAGE_FIELD) {
                    showMessageToast(true, getString(it.second))
                }
            }
        }
    }
}