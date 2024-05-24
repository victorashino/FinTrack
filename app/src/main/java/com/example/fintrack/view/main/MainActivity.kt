package com.example.fintrack.view.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fintrack.data.model.Category
import com.example.fintrack.data.model.Spent
import com.example.fintrack.databinding.ActivityMainBinding
import com.example.fintrack.view.createcategory.CreateCategoryActivity
import com.example.fintrack.view.createspent.CreateSpentActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val spentAdapter: SpentAdapter by lazy { SpentAdapter(::openCreateSpentToUpdate) }

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
        observeTotalValue(viewModel.selectedCategory.value!!)
        viewModel.selectedCategory.value?.let { viewModel.selectCategory(it) }
        setupRecyclerView()
        updateButton(viewModel.selectedCategory.value!!)
    }

    @SuppressLint("DefaultLocale", "SetTextI18n")
    private fun observeTotalValue(category: Category) {
        /*if (category.id == 1) {*/
        viewModel.totalSpentValue.observe(this) { totalValue ->
            if (totalValue != null) {
                binding.txtSum.text = String.format("$%.2f", totalValue)
            } else {
                binding.txtSum.text = "$0.00"
            }
        }
        /*} else {
            viewModel.totalValueByCategory.observe(this) { valueByCategory ->
                if (valueByCategory != null) {
                    binding.txtSum.text = String.format("$%.2f", valueByCategory)
                } else {
                    binding.txtSum.text = "$0.00"
                }
            }
        }*/
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun onCategorySelected(category: Category) {
        viewModel.selectCategory(category)
        categoryAdapter.notifyDataSetChanged()
        updateButton(category)
        observeTotalValue(category)
    }

    private fun setupRecyclerView() {
        val rvCategory = binding.rvCategory
        val rvSpent = binding.rvSpent
        rvCategory.adapter = categoryAdapter
        rvSpent.adapter = spentAdapter

        observeCategoryViewModel()
        observeSpentViewModel()
    }

    private fun observeCategoryViewModel() {
        viewModel.allCategories.observe(this) { categories ->
            categories?.let {
                categoryAdapter.submitList(categories)
                updateButton(categories[0])
                observeTotalValue(categories[0])
            }
        }
    }

    private fun observeSpentViewModel() {
        viewModel.spents.observe(this) { spents ->
            spents?.let {
                spentAdapter.submitList(spents)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateButton(category: Category) {

        if (category.name == "All") {
            binding.txtNewSpent.text = "New category"
            binding.btnAdd.setOnClickListener {
                startActivity(Intent(this, CreateCategoryActivity::class.java))
            }
            observeTotalValue(category)
        } else {
            binding.txtNewSpent.text = "New spent"
            binding.btnAdd.setOnClickListener {
                startActivity(CreateSpentActivity.startInsert(this, category))
            }
            observeTotalValue(category)
        }
    }

    private fun openCreateSpentToUpdate(spent: Spent, category: Category) {
        val intent = CreateSpentActivity.startUpdate(application, spent, category)
        startActivity(intent)
    }

}