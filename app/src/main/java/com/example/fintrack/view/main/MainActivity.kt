package com.example.fintrack.view.main

import android.annotation.SuppressLint
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
import com.example.fintrack.view.createspent.CreateSpentActivity
import com.example.fintrack.view.createcategory.CreateCategoryActivity

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
        observeTotalValue()
        viewModel.selectedCategory.value?.let { viewModel.selectCategory(it) }
        setupRecyclerView()
    }

    private fun observeTotalValue() {
        viewModel.totalSpentValue.observe(this) { totalValue ->
            if (totalValue != null) {
                binding.txtSum.text = String.format("$ %.2f", totalValue)
            } else {
                binding.txtSum.text = "$0.00"
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun onCategorySelected(category: Category) {
        viewModel.selectCategory(category)
        updateButton()
        categoryAdapter.notifyDataSetChanged()
    }

    private fun setupRecyclerView() {
        val rvCategory = binding.rvCategory
        val rvSpent = binding.rvSpent
        rvCategory.adapter = categoryAdapter
        rvSpent.adapter = spentAdapter

        observeCategoryViewModel()
        observeSpentViewModel()
    }

    private fun observeSelectedCategory() {
        viewModel.selectedCategory.observe(this) { category ->
            category?.let {
                onCategorySelected(category)
            }
        }
    }

    private fun observeCategoryViewModel() {
        viewModel.allCategories.observe(this) { categories ->
            categories?.let {
                updateCategoryRecyclerView(categories)
            }
        }
    }

    private fun observeSpentViewModel() {
        viewModel.spents.observe(this) { spents ->
            spents?.let {
                updateAllSpentRecyclerView(spents)
            }
        }
    }

    private fun updateCategoryRecyclerView(categories: List<Category>) {
        categoryAdapter.submitList(categories)
    }

    private fun updateAllSpentRecyclerView(spents: List<Spent>) {
        spentAdapter.submitList(spents)
    }

    private fun updateButton() {
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
                    val color: String = category.color
                    startActivity(CreateSpentActivity.startByMain(this, id, name, color, icon))
                }
            }
        } else {
            Log.i("category_null", "updateButton: category is null")
        }
    }

}