package com.example.fintrack.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.InvalidationTracker
import com.example.fintrack.R
import com.example.fintrack.data.model.Category
import com.example.fintrack.data.model.Spent
import com.example.fintrack.databinding.ActivityMainBinding
import com.example.fintrack.ui.createcategory.CreateCategoryActivity

class MainActivity : AppCompatActivity() {

    private val categoryList = arrayListOf(
        Category(0, "All", R.color.black, R.drawable.ic_game_control),
        Category(0, "Escola", R.color.medium_orange, R.drawable.ic_game_control),
        Category(0, "Aluguel", R.color.water_green, R.drawable.ic_home),
        Category(0, "Mercado", R.color.violet, R.drawable.ic_home),
        Category(0, "Transporte", R.color.ocean_blue, R.drawable.ic_home),
        Category(0, "Faculdade", R.color.red, R.drawable.ic_home),
        Category(0, "IPVA", R.color.pink, R.drawable.ic_home),
        Category(0, "IPTU", R.color.light_brown, R.drawable.ic_home),
        Category(0, "Internet", R.color.blue, R.drawable.ic_home),
        Category(0, "Agua", R.color.light_orange, R.drawable.ic_home),
        Category(0, "Energia", R.color.water_blue, R.drawable.ic_home),
        Category(0, "Lazer", R.color.medium_brown, R.drawable.ic_home),
    )

    private val spentList = arrayListOf(
        Spent(0, "Elden Ring parcela 24x", 17.53f, Category(0, "Lazer", R.color.medium_orange, R.drawable.ic_game_control)),
        Spent(0, "Manutençao carro dia 14/05", 12.51f, Category(0, "Food", R.color.light_yellow, R.drawable.ic_car)),
        Spent(0, "Gasolina mensal", 12.56f, Category(0, "Food", R.color.violet, R.drawable.ic_gas_station)),
        Spent(0, "House rent", 12.000f, Category(0, "Food", R.color.light_green, R.drawable.ic_water_drop)),
        Spent(0, "House rent", 12.000f, Category(0, "Food", R.color.water_green, R.drawable.ic_water_drop)),
        Spent(0, "House rent", 12.000f, Category(0, "Food", R.color.light_brown, R.drawable.ic_credit_card)),
        Spent(0, "House rent", 12.000f, Category(0, "Food", R.color.medium_yellow, R.drawable.ic_electricity)),
        Spent(0, "House rent", 12.000f, Category(0, "Food", R.color.ocean_blue, R.drawable.ic_clothes)),
        Spent(0, "House rent", 12.000f, Category(0, "Food", R.color.red, R.drawable.ic_home)),
        Spent(0, "House rent", 12.000f, Category(0, "Food", R.color.light_green, R.drawable.ic_home)),
        Spent(0, "House rent", 12.000f, Category(0, "Food", R.color.light_green, R.drawable.ic_home)),
        Spent(0, "House rent", 12.000f, Category(0, "Food", R.color.light_green, R.drawable.ic_home)),
        Spent(0, "House rent", 12.000f, Category(0, "Food", R.color.light_green, R.drawable.ic_home)),
        Spent(0, "House rent", 12.000f, Category(0, "Food", R.color.light_green, R.drawable.ic_home)),
        Spent(0, "House rent", 12.000f, Category(0, "Food", R.color.light_green, R.drawable.ic_home)),
        Spent(0, "House rent", 12.000f, Category(0, "Food", R.color.light_green, R.drawable.ic_home)),
        Spent(0, "House rent", 12.000f, Category(0, "Food", R.color.light_green, R.drawable.ic_home)),
        Spent(0, "House rent", 12.000f, Category(0, "Food", R.color.light_green, R.drawable.ic_home)),
        Spent(0, "House rent", 12.000f, Category(0, "Food", R.color.light_green, R.drawable.ic_home)),
        Spent(0, "House rent", 12.000f, Category(0, "Food", R.color.light_green, R.drawable.ic_home)),
        Spent(0, "House rent", 12.000f, Category(0, "Food", R.color.light_green, R.drawable.ic_home)),
        Spent(0, "House rent", 12.000f, Category(0, "Food", R.color.light_green, R.drawable.ic_home)),
        Spent(0, "House rent", 12.000f, Category(0, "Food", R.color.light_green, R.drawable.ic_home)),
    )

    private lateinit var binding: ActivityMainBinding

    private val categoryAdapter: CategoryAdapter by lazy { CategoryAdapter() }

    private val spentAdapter: SpentAdapter by lazy { SpentAdapter() }

    private val viewModel: MainViewModel by viewModels()

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

        val rvCategory = binding.rvCategory
        rvCategory.adapter = categoryAdapter
        categoryAdapter.submitList(categoryList)

        /*setupRecyclerView()
        observeViewModel()*/

        binding.btnNewCategory.setOnClickListener {
            startActivity(Intent(this, CreateCategoryActivity::class.java))
        }
    }

    private fun setupRecyclerView() {
        val categoryAdapter = CategoryAdapter()
        binding.rvCategory.apply {
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun observeViewModel() {
        viewModel.categories.observe(this) { categories ->
            categories?.let {
                updateRecyclerView(categories)
            }
        }
    }

    private fun updateRecyclerView(categories: List<Category>) {
        categoryAdapter.submitList(categories)
    }
}