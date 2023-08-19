package com.example.fragmentsapp.fragment1.nested

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.example.fragmentsapp.R
import com.example.fragmentsapp.databinding.FragmentNestedBinding
import com.example.fragmentsapp.fragment1.MainFragment

internal class NestedFragment : Fragment(R.layout.fragment_nested) {

    private var _binding: FragmentNestedBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNestedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragmentResultListener()
        binding.editTextNestedFragment.setText(requireArguments().getString(NESTED_FRAGMENT_1_EXTRA_KEY))
        binding.buttonNestedFragment.setOnClickListener {
            (requireParentFragment() as NestedListener).openDialog(binding.editTextNestedFragment.text.toString())
        }
    }

    private fun initFragmentResultListener() {
        setFragmentResultListener(NESTED_FRAGMENT_NEW_DATA_REQUEST_KEY) { requestKey, bundle ->
            if (requestKey != NESTED_FRAGMENT_NEW_DATA_REQUEST_KEY) return@setFragmentResultListener
            if (bundle.containsKey(NESTED_FRAGMENT_NEW_DATA_BUNDLE_KEY)) {
                val text = bundle.getString(NESTED_FRAGMENT_NEW_DATA_BUNDLE_KEY)
                text?.let { (requireParentFragment() as NestedListener).updateMainFragmentText(it) }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val NESTED_FRAGMENT_1_TAG = "NestedFragment"
        const val NESTED_FRAGMENT_NEW_DATA_REQUEST_KEY = "NESTED_FRAGMENT_NEW_DATA_REQUEST_KEY"
        const val NESTED_FRAGMENT_NEW_DATA_BUNDLE_KEY = "NESTED_FRAGMENT_NEW_DATA_BUNDLE_KEY"
        private const val NESTED_FRAGMENT_1_EXTRA_KEY = "NEW_TEXT_EXTRA_KEY"

        fun newInstance(newText: String): NestedFragment = NestedFragment().apply {
            arguments = bundleOf(NESTED_FRAGMENT_1_EXTRA_KEY to newText)
        }
    }
}