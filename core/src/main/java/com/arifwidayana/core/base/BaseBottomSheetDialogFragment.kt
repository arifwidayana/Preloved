package com.arifwidayana.core.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.arifwidayana.core.utils.getErrorMessage
import com.arifwidayana.style.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import java.lang.Exception

abstract class BaseBottomSheetDialogFragment<VB : ViewBinding, VM : ViewModel>(
    private val bindingFactory: (LayoutInflater) -> VB
) : BaseContract.BottomSheetDialogFragment, BottomSheetDialogFragment() {
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

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)

    abstract fun initView()
    abstract fun observeData()

    override fun showMessageSnackBar(isEnabled: Boolean, message: String?, exception: Exception?) {
        when {
            exception != null -> {
                Snackbar.make(
                    binding.root,
                    requireContext().getErrorMessage(exception),
                    Toast.LENGTH_SHORT
                ).show()
            }
            isEnabled -> {
                Snackbar.make(binding.root, message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}