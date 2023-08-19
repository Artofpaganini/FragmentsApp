package com.example.fragmentsapp.fragment1.nested

import android.app.Dialog
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import com.example.fragmentsapp.R
import com.example.fragmentsapp.fragment1.MainFragment
import com.example.fragmentsapp.fragment1.nested.NestedFragment.Companion.NESTED_FRAGMENT_NEW_DATA_BUNDLE_KEY
import com.google.android.material.dialog.MaterialAlertDialogBuilder

internal class NestedDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val newText = requireArguments().getString(NESTED_DIALOG_NEW_TEXT_EXTRA_KEY)
        return MaterialAlertDialogBuilder(requireContext())
            .setPositiveButton(R.string.send_data) { dialog, _ ->
                setFragmentResult(
                    NestedFragment.NESTED_FRAGMENT_NEW_DATA_REQUEST_KEY,
                    bundleOf(NESTED_FRAGMENT_NEW_DATA_BUNDLE_KEY to newText)
                )
                (requireParentFragment() as NestedListener).goToParentFragment()
            }
            .setNegativeButton(R.string.cancel) { dialog, _ ->
                dialog.cancel()
            }
            .setCancelable(true)
            .create()
    }

    companion object {
        private const val NESTED_DIALOG_TAG = "NestedDialog"

        private const val NESTED_DIALOG_NEW_TEXT_EXTRA_KEY = "NESTED_DIALOG_NEW_TEXT_EXTRA_KEY"

        fun showDialog(
            fragmentManager: FragmentManager,
            newText: String
        ) {
            if (fragmentManager.findFragmentByTag(NESTED_DIALOG_TAG) != null) return
            NestedDialog().apply {
                arguments = bundleOf(NESTED_DIALOG_NEW_TEXT_EXTRA_KEY to newText)
            }.show(fragmentManager, NESTED_DIALOG_TAG)
        }
    }
}

