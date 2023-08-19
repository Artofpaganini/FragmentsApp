package com.example.fragmentsapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.example.fragmentsapp.databinding.ActivityMainBinding

class MainActivity : FragmentActivity(R.layout.activity_main) {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (supportFragmentManager.findFragmentByTag(Fragment1.FRAGMENT_1_TAG) == null) {
            Log.d("TAG", "onCreate: Fragment1 CREATED FIRST TIME")
            supportFragmentManager.beginTransaction().run {
                val fragment1 = Fragment1.newInstance()
                setReorderingAllowed(true)
                add(R.id.main_fragment_container, fragment1, Fragment1.FRAGMENT_1_TAG)
                commit()
            }
        }
        Log.d("TAG", "onCreate: Fragment1 HAS ALREADY CREATED")

    }
}