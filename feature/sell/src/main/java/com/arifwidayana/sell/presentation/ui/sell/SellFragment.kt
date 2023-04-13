package com.arifwidayana.sell.presentation.ui.sell

import android.app.Activity
import android.net.Uri
import android.os.Environment
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import coil.size.Scale
import com.arifwidayana.core.base.BaseFragment
import com.arifwidayana.core.utils.FieldErrorException
import com.arifwidayana.sell.databinding.FragmentSellBinding
import com.arifwidayana.sell.presentation.ui.category.AddCategoryFragment
import com.arifwidayana.shared.data.network.model.request.sell.SellParamRequest
import com.arifwidayana.shared.data.network.model.request.sell.category.SellCategoryRequest
import com.arifwidayana.shared.data.network.model.request.sell.preview.PreviewRequest
import com.arifwidayana.shared.data.network.model.response.account.UserParamResponse
import com.arifwidayana.shared.utils.Constant
import com.arifwidayana.shared.utils.ext.*
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.io.File

class SellFragment : BaseFragment<FragmentSellBinding, SellViewModel>(
    FragmentSellBinding::inflate
) {
    private var imageUri: Uri? = null
    private lateinit var userParamResponse: UserParamResponse
    private lateinit var listCategory: List<SellCategoryRequest>
    override val viewModel: SellViewModel by inject()

    override fun initView() {
        onView()
        onClick()
    }

    private fun onView() {
        binding.apply {
            viewModel.getUser()
            resetFieldError()
            listCategory = viewModel.listCategoryResult
            etProductCategory.setText(listCategory.joinToString { it.name })
            etProductPrice.changed(
                onTextChanged = {
                    if (it.isEmpty()) etProductPrice.setText("0")
                },
                afterTextChanged = {
                    if (it.isEmpty()) return@changed
                    val parsed = parseCurrencyValue(it)
                    etProductPrice.setText(parsed)
                    etProductPrice.setSelection(parsed.length)
                }
            )
        }
    }

    private fun onClick() {
        binding.apply {
            sivImageProduct.setOnClickListener {
                pickImage()
            }
            etProductCategory.setOnClickListener {
                AddCategoryFragment(viewModel) {
                    onView()
                }.show(parentFragmentManager, null)
            }
            btnPreview.setOnClickListener {
                moveNav(
                    SellFragmentDirections.actionSellFragmentToPreviewFragment(
                        PreviewRequest(
                            sellParamRequest = setDataModel(),
                            userParamResponse = userParamResponse
                        )
                    )
                )
            }
            btnSubmit.setOnClickListener {
                viewModel.createProduct(sellParamRequest = setDataModel())
            }
        }
    }

    override fun observeData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
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
                launch {
                    viewModel.getUserResult.collect {
                        it.source(
                            doOnLoading = {},
                            doOnSuccess = { source ->
                                source.payload?.let { res ->
                                    userParamResponse = res
                                }
                            },
                            doOnError = { error ->
                                showMessageSnackBar(true, exception = error.exception)
                            }
                        )
                    }
                }
            }
        }
    }

    private fun setDataModel(): SellParamRequest {
        binding.apply {
            return SellParamRequest(
                name = etProductName.text.toString(),
                description = etProductDescription.text.toString(),
                basePrice = clearCurrencyValue(etProductPrice.text.toString()).toInt(),
                categoryId = listCategory,
                location = userParamResponse.city,
                image = uriToFile(requireContext(), imageUri)
            )
        }
    }

    private fun pickImage() {
        requireContext().getExternalFilesDir(Environment.DIRECTORY_DCIM)?.let {
            ImagePicker.with(this@SellFragment)
                .crop()
                .saveDir(File(requireContext().externalCacheDir, "image"))
                .galleryMimeTypes(
                    mimeTypes = arrayOf(
                        "image/png",
                        "image/jpg",
                        "image/jpeg"
                    )
                )
                .compress(1024)
                .maxResultSize(1080, 1080)
                .createIntent { intent ->
                    startForProfileImageResult.launch(intent)
                }
        }
    }

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                Activity.RESULT_OK -> {
                    result.data?.data?.let {
                        imageUri = it
                        binding.sivImageProduct.load(it) {
                            scale(Scale.FILL)
                        }
                    }
                }
                ImagePicker.RESULT_ERROR -> {
                    showMessageToast(true, ImagePicker.getError(result.data))
                }
                else -> {
                    showMessageToast(
                        false,
                        getString(com.arifwidayana.style.R.string.task_cancelled)
                    )
                }
            }
        }

    private fun resetFieldError() {
        binding.apply {
            etProductName.changed(onTextChanged = {
                tilProductName.isErrorEnabled = false
            })
            etProductPrice.changed(onTextChanged = {
                tilProductPrice.isErrorEnabled = false
            })
            etProductCategory.changed(onTextChanged = {
                tilProductCategory.isErrorEnabled = false
            })
            etProductDescription.changed(onTextChanged = {
                tilProductDescription.isErrorEnabled = false
            })
        }
    }

    private fun handleErrorField(fieldErrorException: FieldErrorException) {
        binding.apply {
            fieldErrorException.errorFields.forEach {
                if (it.first == Constant.SELL_NAME_FIELD) {
                    tilProductName.error = getString(it.second)
                }
                if (it.first == Constant.SELL_PRICE_FIELD) {
                    tilProductPrice.error = getString(it.second)
                }
                if (it.first == Constant.SELL_CATEGORY_FIELD) {
                    tilProductCategory.error = getString(it.second)
                }
                if (it.first == Constant.SELL_DESCRIPTION_FIELD) {
                    tilProductDescription.error = getString(it.second)
                }
                if (it.first == Constant.SELL_IMAGE_FIELD) {
                    showMessageToast(true, getString(it.second))
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        binding.sivImageProduct.load(imageUri) {
            scale(Scale.FILL)
        }
    }
}