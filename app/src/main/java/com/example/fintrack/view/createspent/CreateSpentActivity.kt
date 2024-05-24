package com.example.fintrack.view.createspent

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

class CreateSpentActivity : AppCompatActivity() {

    companion object {

        private const val EXTRA_CATEGORY = "extra_category"
        private const val EXTRA_SPENT = "extra_spent"

        fun startInsert(context: Context, category: Category): Intent {
            return Intent(context, CreateSpentActivity::class.java)
                .apply {
                    putExtra(EXTRA_CATEGORY, category)
                }
        }

        fun startUpdate(context: Context, spent: Spent, category: Category): Intent {
            return Intent(context, CreateSpentActivity::class.java)
                .apply {
                    putExtra(EXTRA_SPENT, spent)
                    putExtra(EXTRA_CATEGORY, category)
                }
        }
    }

    private lateinit var binding: ActivityCreateSpentBinding

    private val viewModel: CreateSpentViewModel by viewModels {
        CreateSpentViewModel.getVMFactory(application)
    }

    private lateinit var selectedCategory: Category

    private var spent: Spent? = null

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

        val btnSave = binding.btnSave
        val spentName = binding.edtSpentName
        val spentPrice = binding.edtSpentPrice

        selectedCategory = intent.getSerializableExtra(EXTRA_CATEGORY) as Category

        spent = intent.getSerializableExtra(EXTRA_SPENT) as Spent?

        if (spent != null) {
            spentName.setText(spent?.name)
            spentPrice.setText(spent?.value.toString())

            btnSave.setOnClickListener {
                try {
                    val name = spentName.text.toString()
                    val priceString = spentPrice.text.toString()
                    saveOrUpdateSpent(
                        Spent(
                            spent!!.id,
                            name,
                            priceString.toFloat(),
                            selectedCategory.id,
                            selectedCategory
                        )
                    )
                    finish()
                } catch (e: Exception) {
                    Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                }
            }
        } else {

            btnSave.setOnClickListener {
                try {
                    val name = spentName.text.toString()
                    val priceString = spentPrice.text.toString().toFloat()
                    val newSpent = Spent(0, name, priceString, selectedCategory.id, selectedCategory)

                    if (name.isNotEmpty() && priceString > 0) {
                        saveOrUpdateSpent(newSpent)
                        Toast.makeText(this, "$name spent successfully created", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                }

            }
        }

        binding.ctnBack.setOnClickListener {
            finish()
        }
    }

    private fun saveOrUpdateSpent(spent: Spent) {
        viewModel.insertIntoDatabase(spent)
    }

    private fun updateSpent(spent: Spent) {
        viewModel.updateIntoDatabase(spent)
    }
}

/*        binding.btnSave.setOnClickListener {
            val name = binding.edtSpentName.text.toString()
            val priceString = binding.edtSpentPrice.text.toString()

            if (priceString.isNotEmpty() && name.isNotEmpty() && selectedCategory.id != 0) {

                val price = priceString.toFloat()
                val newOrUpdatedSpent = Spent(0, name, price, selectedCategory.id, selectedCategory)

                if (spent == null) {
                    saveSpent(newOrUpdatedSpent)
                    Toast.makeText(this, "$name spent successfully created", Toast.LENGTH_SHORT)
                        .show()
                    finish()
                } else {
                    updateSpent(newOrUpdatedSpent)
                }
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }*/

