package com.example.fintrack.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fintrack.data.model.Category
import com.example.fintrack.data.model.Spent
import com.example.fintrack.databinding.ActivityMainBinding
import com.example.fintrack.ui.createspent.CreateSpentActivity
import com.example.fintrack.ui.createcategory.CreateCategoryActivity
import com.example.fintrack.utils.AppUtils

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val spentAdapter: SpentAdapter by lazy { SpentAdapter() }

    private var categoryAdapter: CategoryAdapter = CategoryAdapter { category ->
        onCategorySelected(category)
    }

    private val viewModel: MainViewModel by viewModels {
        MainViewModel.getVMFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupRecyclerView()

    }

    private fun onCategorySelected(category: Category) {
        viewModel.selectCategory(category)
        updateButton(category)
    }

    private fun updateButton(category: Category) {
        val category = viewModel.selectedCategory.value

        if (category != null) {
            if (category.name == "All") {
                binding.txtNewSpent.text = "New Category"
                binding.btnAdd.setOnClickListener {
                    startActivity(Intent(this, CreateCategoryActivity::class.java))
                }
            } else {
                binding.txtNewSpent.text = "New Spent"
                binding.btnAdd.setOnClickListener {
                    val id = category.id
                    val name = category.name
                    val icon = category.icon
                    val color = category.color
                    startActivity(CreateSpentActivity.startByMain(this, id, name, icon, color))
                }
            }
        } else {
            Log.i(AppUtils.TAG, "updateButton: category is null")
        }
    }

    private fun setupRecyclerView() {
        binding.rvCategory.apply {
            adapter = categoryAdapter
            observeViewModel()
        }
        binding.rvSpent.apply {
            adapter = spentAdapter
            observeViewModel()
        }
    }

    private fun observeViewModel() {
        viewModel.allCategories.observe(this) { categories ->
            categories?.let {
                updateCategoryRecyclerView(categories)
            }
        }

        viewModel.allSpents.observe(this) { spents ->
            spents?.let {
                updateSpentRecyclerView(spents)
            }
        }
    }

    private fun updateCategoryRecyclerView(categories: List<Category>) {
        categoryAdapter.submitList(categories)
    }
    
    private fun updateSpentRecyclerView(spents: List<Spent>) {
        spentAdapter.submitList(spents)
    }

}