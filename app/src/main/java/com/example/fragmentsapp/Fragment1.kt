package com.example.fragmentsapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragmentsapp.databinding.Fragment1Binding

class Fragment1 : Fragment(R.layout.fragment_1) {

    private var _binding: Fragment1Binding? = null

    private val binding get() = _binding!!

    private var listener: Fragment1ButtonClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context !is Fragment1ButtonClickListener) {
            throw Exception("ERROR")
        } else {
            Log.d("TAG", "onAttach: THIS CONTEXT is LISTENER ")

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
        binding.buttonFragment1.setOnClickListener {
            Log.d("TAG", "onViewCreated: PUSH BUTTON GO TO FRAGMENT2")
            listener?.gotoFragment2()
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