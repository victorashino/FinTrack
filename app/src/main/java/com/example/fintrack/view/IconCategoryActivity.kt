package com.example.fintrack.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fintrack.R
import com.example.fintrack.databinding.ActivityIconCategoryBinding

class IconCategoryActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_CATEGORY_NAME = "category.name.extra"
        private const val EXTRA_CATEGORY_COLOR = "category.color.extra"

        fun startIconCategoryActivity(context: Context, categoryName: String?, categoryColor: Int): Intent {
            val intent = Intent(context, IconCategoryActivity::class.java)
                .apply {
                    putExtra(EXTRA_CATEGORY_NAME, categoryName)
                    putExtra(EXTRA_CATEGORY_COLOR, categoryColor)
                }
            return intent
        }
    }

    private lateinit var binding: ActivityIconCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityIconCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val categoryName = intent.getStringExtra(EXTRA_CATEGORY_NAME)
        val categoryColor = intent.getIntExtra(EXTRA_CATEGORY_COLOR, 0)

        /*binding.name.text = categoryName
        setBackgroundColor(binding.root, categoryColor)*/

    }

    fun setBackgroundColor(view: View, colorResId: Int) {
        val color = ContextCompat.getColor(view.context, colorResId)
        view.setBackgroundColor(color)
    }

}