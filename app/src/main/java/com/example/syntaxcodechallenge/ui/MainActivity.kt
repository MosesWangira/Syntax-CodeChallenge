package com.example.syntaxcodechallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.syntaxcodechallenge.R
import com.example.syntaxcodechallenge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Inflate view using dataBinding
         * */
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

    }
}