package com.arifwidayana.account.presentation.ui.account

import androidx.lifecycle.*
import coil.load
import coil.size.Scale
import com.arifwidayana.account.R
import com.arifwidayana.account.databinding.FragmentAccountBinding
import com.arifwidayana.core.base.BaseFragment
import com.arifwidayana.shared.data.network.model.response.account.UserParamResponse
import com.arifwidayana.shared.utils.Navigation.HOMEPAGE_URI
import com.arifwidayana.shared.utils.ext.source
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class AccountFragment : BaseFragment<FragmentAccountBinding, AccountViewModel>(
    FragmentAccountBinding::inflate
) {
    override val viewModel: AccountViewModel by inject()

    override fun initView() {
        onView()
        onClick()
    }

    private fun onView() {
        viewModel.getUser()
    }

    private fun onClick() {
        binding.apply {
            tvProfile.setOnClickListener {
                moveNav(R.id.action_accountFragment_to_profileFragment)
            }
            tvPassword.setOnClickListener {
                moveNav(R.id.action_accountFragment_to_passwordFragment)
            }
            tvOrder.setOnClickListener {
                moveNav(R.id.action_accountFragment_to_orderFragment)
            }
            tvWishlist.setOnClickListener {
                moveNav(R.id.action_accountFragment_to_wishlistFragment)
            }
            tvLogout.setOnClickListener {
                viewModel.logoutUser()
            }
        }
    }

    override fun observeData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                launch {
                    viewModel.validateTokenUserResult.collect {
                        it.source(
                            doOnSuccess = {
                                noticeDialog(
                                    positiveNav = R.id.action_accountFragment_to_login_nav,
                                    negativeNav = HOMEPAGE_URI,
                                    body = com.arifwidayana.style.R.string.body_login
                                )
                            },
                            doOnError = { error ->
                                showMessageSnackBar(true, exception = error.exception)
                            }
                        )
                    }
                }
                launch {
                    viewModel.getUserResult.collect {
                        it.source(
                            doOnSuccess = { result ->
                                setStateView(result.payload)
                            }
                        )
                    }
                }
                launch {
                    viewModel.logoutUserResult.collect {
                        it.source(
                            doOnSuccess = {
                                moveNav(deepLink = HOMEPAGE_URI)
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

    private fun setStateView(data: UserParamResponse?) {
        binding.apply {
            sivProfile.load(data?.imageUrl) {
                placeholder(com.arifwidayana.style.R.drawable.ic_profile)
                scale(Scale.FILL)
            }
        }
    }
}