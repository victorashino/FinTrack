package com.example.fintrack.ui.main

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.fintrack.R
import com.example.fintrack.databinding.ActivityMainBinding
import com.example.fintrack.data.model.Category
import com.example.fintrack.data.model.Spent
import com.example.fintrack.ui.createcategory.CreateCategoryActivity

class MainActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_ID= "EXTRA_ID"
        private const val EXTRA_NAME= "EXTRA_NAME"
        private const val EXTRA_COLOR= "EXTRA_COLOR"
        private const val EXTRA_ICON = "EXTRA_ICON"

        fun startedByCreateCategory(context: Context, id: Int, name: String, color: Int, icon: Int): Intent {
            return Intent(context, MainActivity::class.java).apply {
                putExtra(EXTRA_ID, id)
                putExtra(EXTRA_NAME, name)
                putExtra(EXTRA_COLOR, color)
                putExtra(EXTRA_ICON, icon)
            }
        }
    }

    private val categoryList = arrayListOf<Category>(
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

    private val categoryAdapter: CategoryAdapter by lazy { CategoryAdapter(categoryList) }

    private val spentAdapter: SpentAdapter by lazy { SpentAdapter(spentList) }

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