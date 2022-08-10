package com.example.tbcrevision5.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.tbcrevision5.databinding.ActivityMainBinding
import com.example.tbcrevision5.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val fieldsAdapter by lazy { MyAdapter() }
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.rvFields.adapter = fieldsAdapter
        getData()
    }

    private fun getData() {
        lifecycleScope.launch {
            viewModel.getFields()
            viewModel.flow.collect {
                fieldsAdapter.submitList(it)
            }
        }
    }
}