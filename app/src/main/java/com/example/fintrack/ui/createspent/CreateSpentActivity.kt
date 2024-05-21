package com.example.fintrack.ui.createspent

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fintrack.R
import com.example.fintrack.data.model.Category
import com.example.fintrack.data.model.Spent
import com.example.fintrack.databinding.ActivityCreateSpentBinding
import com.example.fintrack.ui.createcategory.CreateCategoryViewModel

class CreateSpentActivity : AppCompatActivity() {

    companion object {

        private const val EXTRA_CATEGORY_ID = "extra_category_id"
        private const val EXTRA_CATEGORY_NAME = "extra_category_name"
        private const val EXTRA_CATEGORY_COLOR = "extra_category_color"
        private const val EXTRA_CATEGORY_ICON = "extra_category_icon"

        fun startByMain(context: Context, id: Int, name: String, color: Int, icon: Int): Intent {
            return Intent(context, CreateSpentActivity::class.java)
                .apply {
                    putExtra(EXTRA_CATEGORY_ID, id)
                    putExtra(EXTRA_CATEGORY_NAME, name)
                    putExtra(EXTRA_CATEGORY_COLOR, color)
                    putExtra(EXTRA_CATEGORY_ICON, icon)
                }
        }
    }

    private lateinit var binding: ActivityCreateSpentBinding

    private lateinit var selectedCategory: Category

    private val viewModel: CreateSpentViewModel by viewModels {
        CreateSpentViewModel.getVMFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCreateSpentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val categoryId = intent.getIntExtra(EXTRA_CATEGORY_ID, 0)
        val categoryName = intent.getStringExtra(EXTRA_CATEGORY_NAME)
        val categoryColor = intent.getIntExtra(EXTRA_CATEGORY_COLOR, 0)
        val categoryIcon = intent.getIntExtra(EXTRA_CATEGORY_ICON, 0)

        selectedCategory = Category(categoryId, categoryName!!, categoryColor, categoryIcon)

        val name = binding.edtSpentName.text.toString()
        val priceString = binding.edtSpentPrice.text.toString()

        binding.btnSave.setOnClickListener {
            if (priceString.isNotEmpty() && name.isNotEmpty()) {
                val price = priceString.toFloat()
                val newSpent = Spent(0, name, price, selectedCategory.id, selectedCategory)
                saveSpent(newSpent)
                finish()
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }

            binding.icBack.setOnClickListener {
                finish()
            }
        }
    }

    private fun saveSpent(spent: Spent) {
        viewModel.insertIntoDatabase(spent)
    }
}