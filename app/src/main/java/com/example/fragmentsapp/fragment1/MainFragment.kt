package com.example.fragmentsapp.fragment1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.setFragmentResultListener
import com.example.fragmentsapp.R
import com.example.fragmentsapp.databinding.FragmentMainBinding
import com.example.fragmentsapp.fragment1.nested.NestedDialog
import com.example.fragmentsapp.fragment1.nested.NestedFragment
import com.example.fragmentsapp.fragment1.nested.NestedListener

internal class MainFragment : Fragment(R.layout.fragment_main), NestedListener {

    private var _binding: FragmentMainBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonMainFragment.setOnClickListener {
            if (childFragmentManager.findFragmentByTag(NestedFragment.NESTED_FRAGMENT_1_TAG) == null) {
                childFragmentManager.commit {
                    setReorderingAllowed(true)
                    add(
                        R.id.parent_fragment_container,
                        NestedFragment.newInstance(binding.textMainFragment.text.toString())
                    )
                    addToBackStack(NestedFragment.NESTED_FRAGMENT_1_TAG)
                }
            }
        }
    }

    override fun openDialog(newText: String) {
        Log.w("TAG", "openDialog: OPEN DIALOG")
        NestedDialog.showDialog(fragmentManager = childFragmentManager, newText = newText)
    }

    override fun goToParentFragment() {
        childFragmentManager.popBackStack()
    }

    override fun updateMainFragmentText(newText: String) {
        binding.textMainFragment.text = newText
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "MainFragment"
        const val MAIN_FRAGMENT_NEW_DATA_REQUEST_KEY = "MAIN_FRAGMENT_NEW_DATA_REQUEST_KEY"
        const val MAIN_FRAGMENT_NEW_DATA_BUNDLE_KEY = "MAIN_FRAGMENT_NEW_DATA_BUNDLE_KEY"

        fun newInstance(): MainFragment = MainFragment()
    }
}