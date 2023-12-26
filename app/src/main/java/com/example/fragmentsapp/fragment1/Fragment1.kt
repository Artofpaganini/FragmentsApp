package com.example.fragmentsapp.fragment1

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.fragmentsapp.R
import com.example.fragmentsapp.databinding.Fragment1Binding
import com.example.fragmentsapp.fragment2.Fragment2
import java.lang.RuntimeException

internal class Fragment1 : Fragment(R.layout.fragment_1) {

    private var _binding: Fragment1Binding? = null

    private val binding get() = _binding!!

    private var listener: Fragment1ButtonClickListener? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context !is Fragment1ButtonClickListener) {
            throw RuntimeException("Message Error")
        } else {
            listener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = Fragment1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val newText = arguments?.getString(FRAGMENT_1_NEW_TEXT_EXTRA_KEY)
        if (!newText.isNullOrEmpty()) {
            binding.textFragment1.text = newText
        }
        binding.buttonFragment1.setOnClickListener {
            listener?.goToFragment2()
            //            (requireContext() as Fragment1ButtonClickListener).goToFragment2WithNewText("New Text Here")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val FRAGMENT_1_TAG = "Fragment1"
        private const val FRAGMENT_1_NEW_TEXT_EXTRA_KEY = "NEW_TEXT_EXTRA_KEY"

        fun newInstance(): Fragment1 = Fragment1()

        fun newInstance(newText: String): Fragment1 = Fragment1().apply {
            arguments = bundleOf(FRAGMENT_1_NEW_TEXT_EXTRA_KEY to newText)
            //          По старому писали так
            //            arguments = Bundle().apply {
            //                putString(NEW_TEXT_EXTRA_KEY, newText)
            //            }
        }
    }
}