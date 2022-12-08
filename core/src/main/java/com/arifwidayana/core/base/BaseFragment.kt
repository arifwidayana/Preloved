package com.arifwidayana.core.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import java.lang.Exception

abstract class BaseFragment<VB: ViewBinding, VM: ViewModel>(
    private val bindingFactory: (LayoutInflater) -> VB
): BaseView, Fragment() {
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

    @SuppressLint("DiscouragedApi")
    override fun navPopUp(idFragment: String?, inclusive: Boolean): NavOptions {
        return NavOptions.Builder().setPopUpTo(
            destinationId = resources.getIdentifier(
                idFragment,
                "id",
                requireContext().packageName
            ),
            inclusive = inclusive
        ).build()
    }

    override fun moveNav() {
        findNavController().popBackStack()
    }

    override fun moveNav(navUp: Int) {
        findNavController().navigate(navUp)
    }

    override fun moveNav(direction: NavDirections) {
        findNavController().navigate(direction)
    }

    override fun showLoading(isVisible: Boolean) { }
    override fun showContent(isVisible: Boolean) { }
    override fun showContentEmpty(isVisible: Boolean) { }

    override fun showMessageToast(isEnabled: Boolean, message: String?, exception: Exception?) {
        when{
            isEnabled -> {
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun showMessageSnackBar(isEnabled: Boolean, message: String?, exception: Exception?) {
        when{
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