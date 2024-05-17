
package com.example.fintrack.view

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.example.fintrack.R
import com.example.fintrack.databinding.ActivityMainBinding
import com.example.fintrack.databinding.AddCategoryItemBinding
import com.example.fintrack.model.Category
import com.example.fintrack.model.Spent
import com.example.fintrack.view.adapter.CategoryAdapter
import com.example.fintrack.view.adapter.SpentAdapter

class MainActivity : AppCompatActivity() {

    private val categoryList = arrayListOf(
        Category(0, "All", 5, 5),
        Category(0, "Mercado", 5, 5),
        Category(0, "Escola", 5, 5),
        Category(0, "Carro", 5, 5),
        Category(0, "Food", 5, 5),
        Category(0, "Food", 5, 5),
        Category(0, "Compras", 5, 5),
        Category(0, "Food", 5, 5),
        Category(0, "Investimento", 5, 5),
        Category(0, "Lazer", 5, 5),
        Category(0, "Faculdade", 5, 5),
        Category(0, "Food", 5, 5),
        Category(0, "Veterinario", 5, 5),
        Category(0, "Food", 5, 5),
    )

    private val spentList = arrayListOf(
        Spent(0, "Faculdade", 17.000f, Category(0, "Food", R.color.green, R.drawable.ic_game_control)),
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
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val rvSpent = binding.rvSpent
        rvSpent.adapter = spentAdapter
        spentAdapter.submitList(spentList)

        val rvCategory = findViewById<RecyclerView>(R.id.rvCategory)
        rvCategory.adapter = categoryAdapter
        categoryAdapter.submitList(categoryList)

        binding.btnNewCategory.setOnClickListener {
            startActivity(Intent(this, CreateCategoryActivity::class.java))
        }

    }
}