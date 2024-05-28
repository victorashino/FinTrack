package com.example.fintrack.view.main

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fintrack.R
import com.example.fintrack.data.model.Category
import com.example.fintrack.data.model.Spent
import com.example.fintrack.databinding.ActivityMainBinding
import com.example.fintrack.view.createcategory.CreateCategoryActivity
import com.example.fintrack.view.createspent.CreateSpentActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val spentAdapter: SpentAdapter by lazy {
        SpentAdapter(::openCreateSpentToUpdate) { spent ->
            dialogDeleteSpent(spent)
        }
    }

    private var categoryAdapter: CategoryAdapter = CategoryAdapter({ category ->
        onCategorySelected(category)
    },
        { category ->
            dialogDeleteCategory(category)
        })

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
        viewModel.selectedCategory.value?.let { viewModel.selectCategory(it) }
        setupRecyclerView()
        updateButton(viewModel.selectedCategory.value!!)
    }

    @SuppressLint("DefaultLocale", "SetTextI18n")
    private fun observeTotalValue() {
        viewModel.totalValueByCategory.observe(this) { valueByCategory ->
            if (valueByCategory != null) {
                binding.txtSum.text = String.format("$%.2f", valueByCategory)
            } else {
                binding.txtSum.text = "$0.40"
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun onCategorySelected(category: Category) {
        viewModel.selectCategory(category)
        categoryAdapter.notifyDataSetChanged()
        updateButton(category)
    }

    @SuppressLint("SetTextI18n")
    private fun dialogDeleteCategory(category: Category) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val dialogTitle: TextView = dialog.findViewById(R.id.dialogTitle)
        dialogTitle.text = "Delete ${category.name}"

        val dialogMessage: TextView = dialog.findViewById(R.id.dialogMessage)
        dialogMessage.text = "This will exclude all related expenses!"

        val okButton: TextView = dialog.findViewById(R.id.btnOk)
        val cancelButton: TextView = dialog.findViewById(R.id.btnCancel)

        okButton.setOnClickListener {
            viewModel.deleteCategoryById(category.id)
            dialog.dismiss()
        }

        cancelButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    @SuppressLint("SetTextI18n")
    private fun dialogDeleteSpent(spent: Spent) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val dialogTitle: TextView = dialog.findViewById(R.id.dialogTitle)
        dialogTitle.text = "Delete ${spent.name}?"

        val okButton: TextView = dialog.findViewById(R.id.btnOk)
        okButton.setOnClickListener {
            viewModel.deleteSpentById(spent.id)
            dialog.dismiss()
        }

        val cancelButton: TextView = dialog.findViewById(R.id.btnCancel)
        cancelButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
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

                if (categories.isNotEmpty()) {
                    updateButton(categories.first())
                    observeTotalValue()
                }
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
            observeTotalValue()
        } else {
            binding.txtNewSpent.text = "New spent"
            binding.btnAdd.setOnClickListener {
                startActivity(CreateSpentActivity.startInsert(this, category))
            }
            observeTotalValue()
        }
    }

    private fun openCreateSpentToUpdate(spent: Spent, category: Category) {
        val intent = CreateSpentActivity.startUpdate(application, spent, category)
        startActivity(intent)
    }

}