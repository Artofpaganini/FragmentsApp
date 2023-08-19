package com.example.fragmentsapp.fragment2

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.fragmentsapp.R
import com.example.fragmentsapp.databinding.Fragment2Binding
import com.example.fragmentsapp.fragment1.Fragment1.Companion.FRAGMENT_1_NEW_DATA_BUNDLE_KEY
import com.example.fragmentsapp.fragment1.Fragment1.Companion.FRAGMENT_1_NEW_DATA_REQUEST_KEY
import com.example.fragmentsapp.fragment1.Fragment1ButtonClickListener
import java.lang.RuntimeException

internal class Fragment2 : Fragment(R.layout.fragment_2) {

    private var _binding: Fragment2Binding? = null

    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context !is Fragment1ButtonClickListener) {
            throw RuntimeException("Message Error")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = Fragment2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editTextFragment2.setText(requireArguments().getString(NEW_TEXT_EXTRA_KEY))
        binding.backToFragment1.setOnClickListener {
            setFragmentResult(
                FRAGMENT_1_NEW_DATA_REQUEST_KEY,
                bundleOf(FRAGMENT_1_NEW_DATA_BUNDLE_KEY to binding.editTextFragment2.text.toString())
            )
            (requireActivity() as Fragment2ButtonClickListener).backToFragment1WithChanges()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val FRAGMENT_2_TAG = "Fragment2"

        private const val NEW_TEXT_EXTRA_KEY = "NEW_TEXT_EXTRA_KEY"

        fun newInstance(newText: String): Fragment2 = Fragment2().apply {
            arguments = bundleOf(NEW_TEXT_EXTRA_KEY to newText)
            //          По старому писали так
            //            arguments = Bundle().apply {
            //                putString(NEW_TEXT_EXTRA_KEY, newText)
            //            }
        }

        fun newInstance(): Fragment2 = Fragment2()
    }
}