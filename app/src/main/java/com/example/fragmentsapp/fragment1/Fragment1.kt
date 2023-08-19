package com.example.fragmentsapp.fragment1

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragmentsapp.R
import com.example.fragmentsapp.databinding.Fragment1Binding
import java.lang.RuntimeException

internal class Fragment1 : Fragment(R.layout.fragment_1) {

    private var _binding: Fragment1Binding? = null

    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context !is Fragment1ButtonClickListener){
            throw RuntimeException("Message Error")
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
        binding.buttonFragment1.setOnClickListener {
//            (requireActivity() as Fragment1ButtonClickListener).goToFragment2()
            (requireContext() as Fragment1ButtonClickListener).goToFragment2WithNewText("New Text Here")

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val FRAGMENT_1_TAG = "Fragment1"

        fun newInstance(): Fragment1 = Fragment1()
    }
}