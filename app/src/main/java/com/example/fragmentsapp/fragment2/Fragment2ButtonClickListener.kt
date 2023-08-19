package com.example.fragmentsapp.fragment2

internal interface Fragment2ButtonClickListener {
    fun backToFragment1()
    fun openFragment1Again()
    fun openFragment2AgainWithNewText(newText: String)
}