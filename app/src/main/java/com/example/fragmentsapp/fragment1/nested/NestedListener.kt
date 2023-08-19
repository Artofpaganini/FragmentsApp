package com.example.fragmentsapp.fragment1.nested

internal interface NestedListener {
    fun openDialog(newText: String)
    fun goToParentFragment()
    fun updateMainFragmentText(newText: String)
}