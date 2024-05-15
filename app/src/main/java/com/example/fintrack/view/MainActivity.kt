
package com.example.fintrack.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fintrack.R
import com.example.fintrack.databinding.ActivityMainBinding
import com.example.fintrack.model.Category
import com.example.fintrack.model.Spent

class MainActivity : AppCompatActivity() {

    private val categoryList = arrayListOf(
        Category(0, "Food", 5, 5),
        Category(0, "Food", 5, 5),
        Category(0, "Food", 5, 5),
        Category(0, "Food", 5, 5),
        Category(0, "Food", 5, 5),
        Category(0, "Food", 5, 5),
        Category(0, "Food", 5, 5),
        Category(0, "Food", 5, 5),
        Category(0, "Food", 5, 5),
        Category(0, "Food", 5, 5),
        Category(0, "Food", 5, 5),
        Category(0, "Food", 5, 5),
        Category(0, "Food", 5, 5),
        Category(0, "Food", 5, 5),
    )

    private val spentList = arrayListOf(
        Spent(0, "House rent", 12.000f, Category(0, "Food", R.color.green, R.drawable.ic_home)),
        Spent(0, "House rent", 12.000f, Category(0, "Food", R.color.green, R.drawable.ic_home)),
        Spent(0, "House rent", 12.000f, Category(0, "Food", R.color.green, R.drawable.ic_home)),
        Spent(0, "House rent", 12.000f, Category(0, "Food", R.color.green, R.drawable.ic_home)),
        Spent(0, "House rent", 12.000f, Category(0, "Food", R.color.green, R.drawable.ic_home)),
        Spent(0, "House rent", 12.000f, Category(0, "Food", R.color.green, R.drawable.ic_home)),
        Spent(0, "House rent", 12.000f, Category(0, "Food", R.color.green, R.drawable.ic_home)),
        Spent(0, "House rent", 12.000f, Category(0, "Food", R.color.green, R.drawable.ic_home)),
        Spent(0, "House rent", 12.000f, Category(0, "Food", R.color.green, R.drawable.ic_home)),
        Spent(0, "House rent", 12.000f, Category(0, "Food", R.color.green, R.drawable.ic_home)),
        Spent(0, "House rent", 12.000f, Category(0, "Food", R.color.green, R.drawable.ic_home)),
        Spent(0, "House rent", 12.000f, Category(0, "Food", R.color.green, R.drawable.ic_home)),
        Spent(0, "House rent", 12.000f, Category(0, "Food", R.color.green, R.drawable.ic_home)),
    )

    private lateinit var binding: ActivityMainBinding

    private val categoryAdapter: CategoryAdapter by lazy { CategoryAdapter() }

    private val spentAdapter: SpentAdapter by lazy { SpentAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.icAddCategory.setOnClickListener {
            startActivity(Intent(this, CreateCategoryActivity::class.java))
        }

        val rvCategory = binding.rvCategory
        val rvSpent = binding.rvSpent

        rvCategory.adapter = categoryAdapter
        rvSpent.adapter = spentAdapter

        categoryAdapter.submitList(categoryList)
        spentAdapter.submitList(spentList)
    }
}