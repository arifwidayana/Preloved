package com.arifwidayana.account.presentation.ui.account

import androidx.lifecycle.lifecycleScope
import coil.load
import coil.size.Scale
import com.arifwidayana.account.R
import com.arifwidayana.account.databinding.FragmentAccountBinding
import com.arifwidayana.core.base.BaseFragment
import com.arifwidayana.shared.data.network.model.response.account.UserParamResponse
import com.arifwidayana.shared.utils.ext.source
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
        lifecycleScope.apply {
            launchWhenCreated {
                viewModel.getUserResult.collect {
                    it.source(
                        doOnSuccess = { result ->
                            setStateView(result.payload)
                        },
                        doOnError = { error ->
                            showMessageSnackBar(true, exception = error.exception)
                        }
                    )
                }
            }
            launchWhenCreated {
                viewModel.logoutUserResult.collect {
                    it.source(
                        doOnSuccess = {
                            moveNav(R.id.action_accountFragment_to_home_nav)
                        },
                        doOnError = { error ->
                            showMessageSnackBar(true, exception = error.exception)
                        }
                    )
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