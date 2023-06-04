package com.arifwidayana.sale.presentation.ui.sale

import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import com.arifwidayana.core.base.BaseFragment
import com.arifwidayana.sale.databinding.FragmentSaleBinding
import com.arifwidayana.sale.presentation.adapter.SaleAdapter
import com.arifwidayana.shared.data.network.model.response.account.UserParamResponse
import com.arifwidayana.shared.utils.Constant
import com.arifwidayana.shared.utils.Navigation
import com.arifwidayana.shared.utils.ext.source
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class SaleFragment : BaseFragment<FragmentSaleBinding, SaleViewModel>(
    FragmentSaleBinding::inflate
) {
    override val viewModel: SaleViewModel by inject()

    override fun initView() {
        onView()
        onClick()
    }

    private fun onView() {
        viewModel.getUser()
        tabLayout()
    }

    private fun onClick() {
        binding.apply {
            btnChange.setOnClickListener {
                moveNav(Navigation.PROFILE_URI)
            }
        }
    }

    override fun observeData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                launch {
                    viewModel.getUserResult.collect {
                        it.source(
                            doOnLoading = {},
                            doOnSuccess = { source ->
                                setStateView(source.payload)
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

    private fun tabLayout() {
        binding.apply {
            val adapter = SaleAdapter(requireActivity().supportFragmentManager, lifecycle)
            val listTab = resources.getStringArray(com.arifwidayana.style.R.array.tab_feature_sale)
            val listIcon = Constant.LIST_ICON
            vpContent.adapter = adapter
            TabLayoutMediator(tlFeature, vpContent) { tab, position ->
                tab.text = listTab[position]
                tab.icon = ResourcesCompat.getDrawable(resources, listIcon[position], null)
            }.attach()
        }
    }

    private fun setStateView(data: UserParamResponse?) {
        binding.apply {
            data?.let {
                sivProfileUser.load(it.imageUrl)
                tvNameUser.text = it.fullName
                tvLocationUser.text = it.city
            }
        }
    }
}
