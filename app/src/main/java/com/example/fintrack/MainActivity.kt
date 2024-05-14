package com.example.fintrack

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fintrack.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val list = arrayListOf(
        Category(0, "Food", "blue", "0"),
        Category(0, "Food", "blue", "0"),
        Category(0, "Food", "blue", "0"),
        Category(0, "Food", "blue", "0"),
        Category(0, "Food", "blue", "0"),
        Category(0, "Food", "blue", "0"),
        Category(0, "Food", "blue", "0"),
        Category(0, "Food", "blue", "0"),
        Category(0, "Food", "blue", "0"),
        Category(0, "Food", "blue", "0"),
        Category(0, "Food", "blue", "0"),
        Category(0, "Food", "blue", "0")
    )

    private lateinit var binding: ActivityMainBinding

    private val adapter: CategoryAdapter by lazy { CategoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rvCategory = binding.rvCategory
        rvCategory.adapter = adapter
        adapter.submitList(list)
    }
}