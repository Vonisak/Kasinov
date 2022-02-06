package com.example.tinkofflab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tinkofflab.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pager.adapter = PagerAdapter(this)

        TabLayoutMediator(binding.tabs, binding.pager) { tab, position ->
            when (position) {
                0 -> tab.text = resources.getText(R.string.random_title)
                1 -> tab.text = resources.getText(R.string.latest_title)
                2 -> tab.text = resources.getText(R.string.hot_title)
                3 -> tab.text = resources.getText(R.string.top_title)
            }
        }.attach()
    }
}