package com.example.fragmentsapp

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.fragmentsapp.databinding.ActivityMainBinding
import com.example.fragmentsapp.fragment1.MainFragmentListener
import com.example.fragmentsapp.fragment1.MainFragment

internal class MainActivity : FragmentActivity(R.layout.activity_main) {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (supportFragmentManager.findFragmentByTag(MainFragment.TAG) == null) {
            supportFragmentManager.beginTransaction().run {
                val mainFragment = MainFragment.newInstance()
                setReorderingAllowed(true)
                add(R.id.main_fragment_container, mainFragment, MainFragment.TAG)
                setPrimaryNavigationFragment(mainFragment)
                //недобавляем первый фрагмент в бэкстэк
                commit()
            }
        }
    }
}