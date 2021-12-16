package com.example.github.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.github.R
import com.example.github.presentation.home.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadInputFragment()
    }

    private fun loadInputFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, HomeFragment()).commit()
    }
}