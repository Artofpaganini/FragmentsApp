package com.example.fragmentsapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.example.fragmentsapp.databinding.ActivityMainBinding
import com.example.fragmentsapp.fragment1.Fragment1
import com.example.fragmentsapp.fragment1.Fragment1ButtonClickListener
import com.example.fragmentsapp.fragment2.Fragment2
import com.example.fragmentsapp.fragment2.Fragment2ButtonClickListener

internal class MainActivity : FragmentActivity(R.layout.activity_main),
    Fragment1ButtonClickListener,
    Fragment2ButtonClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (supportFragmentManager.findFragmentByTag(Fragment1.FRAGMENT_1_TAG) == null) {
            supportFragmentManager.beginTransaction().run {
                val fragment1 = Fragment1.newInstance()
                setReorderingAllowed(true)
                add(R.id.main_fragment_container, fragment1, Fragment1.FRAGMENT_1_TAG)
                //недобавляем первый фрагмент в бэкстэк
                commit()
            }
        }
    }

    override fun goToFragment2() {
        supportFragmentManager.beginTransaction().run {
            val fragment2 = Fragment2.newInstance()
            setReorderingAllowed(true)
            add(R.id.main_fragment_container, fragment2, Fragment2.FRAGMENT_2_TAG)
            addToBackStack(Fragment2.FRAGMENT_2_TAG)
            commit()
        }
    }

    override fun goToFragment2WithNewText(text: String) {
        supportFragmentManager.beginTransaction().run {
            val fragment2 = Fragment2.newInstance(newText = text)
            setReorderingAllowed(true)
            replace(R.id.main_fragment_container, fragment2, Fragment2.FRAGMENT_2_TAG)
            addToBackStack(Fragment2.FRAGMENT_2_TAG)
            commit()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun backToFragment1() {
        supportFragmentManager.popBackStack()
    }

    override fun openFragment1Again() {
        if (supportFragmentManager.findFragmentByTag(Fragment1.FRAGMENT_1_TAG) == null) {
            Log.w("TAG", "openFragment1Again: Fragment1 HAS CREATED AGAIN ")
            supportFragmentManager.beginTransaction().run {
                val fragment1 = Fragment1.newInstance()
                setReorderingAllowed(true)
                add(R.id.main_fragment_container, fragment1, Fragment1.FRAGMENT_1_TAG)
                addToBackStack(Fragment1.FRAGMENT_1_TAG)
                commit()
            }
        } else if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack(Fragment1.FRAGMENT_1_TAG, 0)
            Log.w("TAG", "openFragment1Again: Fragment1 HAS TAKEN FROM BACKSTACK")
        }
    }

    override fun openFragment2AgainWithNewText(newText: String) {
        Log.w("TAG", "openFragment1Again: Fragment1 HAS CREATED AGAIN WITH NEW TEXT ")
        supportFragmentManager.beginTransaction().run {
            val fragment1 = Fragment1.newInstance(newText)
            setReorderingAllowed(true)
            replace(R.id.main_fragment_container, fragment1, Fragment1.FRAGMENT_1_TAG)
            addToBackStack(Fragment1.FRAGMENT_1_TAG)
            commit()
        }
    }
}