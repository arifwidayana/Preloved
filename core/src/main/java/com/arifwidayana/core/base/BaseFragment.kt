package com.arifwidayana.core.base

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.arifwidayana.core.utils.getErrorMessage
import com.google.android.material.snackbar.Snackbar
import java.lang.Exception

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel>(
    private val bindingFactory: (LayoutInflater) -> VB
) : BaseContract, Fragment() {
    private var _binding: VB? = null
    protected val binding: VB get() = _binding!!
    protected abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingFactory.invoke(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeData()
    }

    abstract fun initView()
    abstract fun observeData()

    override fun moveNavigateUp() {
        findNavController().popBackStack()
    }

    override fun moveNav(navUp: Int, args: Bundle?) {
        findNavController().navigate(navUp, args)
    }

    override fun moveNav(direction: NavDirections) {
        findNavController().navigate(direction)
    }

    override fun moveNav(deepLink: String?) {
        findNavController().navigate(
            deepLink = Uri.parse(deepLink)
        )
    }

    @SuppressLint("DiscouragedApi")
    override fun moveNav(deepLink: String, idFragmentPopUp: String?, inclusive: Boolean) {
        findNavController().navigate(
            request = NavDeepLinkRequest.Builder
                .fromUri(deepLink.toUri())
                .build(),
            navOptions = NavOptions.Builder()
                .setRestoreState(true)
                .setLaunchSingleTop(true)
                .setPopUpTo(
                    destinationId = resources.getIdentifier(
                        idFragmentPopUp,
                        "id",
                        requireContext().packageName
                    ),
                    inclusive = inclusive,
                    saveState = true
                ).build()
        )
    }

    override fun showLoading(isVisible: Boolean) {}
    override fun showContent(isVisible: Boolean) {}
    override fun showContentEmpty(isVisible: Boolean) {}

    override fun showMessageToast(isEnabled: Boolean, message: String?, exception: Exception?) {
        when {
            isEnabled -> {
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun showMessageSnackBar(isEnabled: Boolean, message: String?, exception: Exception?) {
        when {
            exception != null -> {
                Snackbar.make(binding.root, requireContext().getErrorMessage(exception), Toast.LENGTH_SHORT).show()
            }
            isEnabled -> {
                Snackbar.make(binding.root, message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}