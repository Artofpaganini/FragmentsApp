package com.example.fragmentsapp.fragment1

internal interface Fragment1ButtonClickListener {

    fun goToFragment2()

    fun goToFragment2WithNewText(text: String)
}