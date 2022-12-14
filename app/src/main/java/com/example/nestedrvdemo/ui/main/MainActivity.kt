package com.example.nestedrvdemo.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nestedrvdemo.ui.main.adapter.OuterAdapter
import com.example.nestedrvdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val adapter by lazy {
        OuterAdapter(viewModel::onNestedItemClick)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.outerRv.layoutManager = LinearLayoutManager(this)
        binding.outerRv.adapter = adapter

        viewModel.state.asLiveData().observe(this) { payload ->
            adapter.submitList(payload.items)
        }
    }
}