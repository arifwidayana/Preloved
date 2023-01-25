package com.arifwidayana.splash.presentation.ui

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import com.arifwidayana.core.base.BaseFragment
import com.arifwidayana.shared.utils.Navigation.HOMEPAGE_URI
import com.arifwidayana.shared.utils.Navigation.SPLASH
import com.arifwidayana.splash.databinding.FragmentSplashBinding
import org.koin.android.ext.android.inject

class SplashFragment : BaseFragment<FragmentSplashBinding, ViewModel>(
    FragmentSplashBinding::inflate
) {
    override val viewModel: ViewModel by inject()
    override fun initView() {
        Handler(Looper.getMainLooper()).postDelayed({
            moveNav(
                deepLink = HOMEPAGE_URI,
                idFragmentPopUp = SPLASH
            )
        }, 5000)
    }
    override fun observeData() { }
}