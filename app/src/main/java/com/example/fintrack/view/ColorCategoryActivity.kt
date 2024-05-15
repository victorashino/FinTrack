package com.example.fintrack.view

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fintrack.R
import com.example.fintrack.databinding.ActivityColorCategoryBinding
import com.google.android.material.snackbar.Snackbar

class ColorCategoryActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_CATEGORY_NAME = "category.name.extra"

        fun startColorCategoryActivity(context: Context, categoryName: String): Intent {
            val intent = Intent(context, ColorCategoryActivity::class.java)
                .apply {
                    putExtra(EXTRA_CATEGORY_NAME, categoryName)
                }
            return intent
        }
    }

    private lateinit var binding: ActivityColorCategoryBinding

    private var selectedColor: Int? = null

    private val colors = arrayOf(
        R.color.black,
        R.color.red,
        R.color.violet,
        R.color.ocean_blue,
        R.color.blue,
        R.color.water_blue,
        R.color.water_green,
        R.color.green,
        R.color.light_yellow,
        R.color.medium_yellow,
        R.color.light_orange,
        R.color.medium_orange,
        R.color.brown,
        R.color.grey,
        R.color.pink
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityColorCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.colorBlack.setOnClickListener { onColorClicked(it) }
        binding.colorRed.setOnClickListener { onColorClicked(it) }
        binding.colorViolet.setOnClickListener { onColorClicked(it) }
        binding.colorOceanBlue.setOnClickListener { onColorClicked(it) }
        binding.colorBlue.setOnClickListener { onColorClicked(it) }
        binding.colorWaterBlue.setOnClickListener { onColorClicked(it) }
        binding.colorWaterGreen.setOnClickListener { onColorClicked(it) }
        binding.colorGreen.setOnClickListener { onColorClicked(it) }
        binding.colorLightYellow.setOnClickListener { onColorClicked(it) }
        binding.colorMediumYellow.setOnClickListener { onColorClicked(it) }
        binding.colorLightOrange.setOnClickListener { onColorClicked(it) }
        binding.colorMediumOrange.setOnClickListener { onColorClicked(it) }
        binding.colorBrown.setOnClickListener { onColorClicked(it) }
        binding.colorGrey.setOnClickListener { onColorClicked(it) }
        binding.colorPink.setOnClickListener { onColorClicked(it) }

        binding.icBack.setOnClickListener {
            finish()
        }

        binding.btnOk.setOnClickListener {
            Log.d("Color", "Selected color: $selectedColor")
            val categoryName = intent.getStringExtra(EXTRA_CATEGORY_NAME)
            if (selectedColor != null && !categoryName.isNullOrEmpty()) {
                startActivity(
                    IconCategoryActivity
                        .startIconCategoryActivity(this, categoryName, selectedColor!!)
                )
            } else {
                Snackbar.make(this, binding.root, "Please select a color", Snackbar.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun onColorClicked(view: View) {
        hideAllCheckIcons()

        when (view.id) {
            R.id.colorBlack -> binding.checkIconBlack.visibility = View.VISIBLE
            R.id.colorRed -> binding.checkIconRed.visibility = View.VISIBLE
            R.id.colorViolet -> binding.checkIconViolet.visibility = View.VISIBLE
            R.id.colorOceanBlue -> binding.checkIconOceanBlue.visibility = View.VISIBLE
            R.id.colorBlue -> binding.checkIconBlue.visibility = View.VISIBLE
            R.id.colorWaterBlue -> binding.checkIconWaterBlue.visibility = View.VISIBLE
            R.id.colorWaterGreen -> binding.checkIconWaterGreen.visibility = View.VISIBLE
            R.id.colorGreen -> binding.checkIconGreen.visibility = View.VISIBLE
            R.id.colorLightYellow -> binding.checkIconLightYellow.visibility = View.VISIBLE
            R.id.colorMediumYellow -> binding.checkIconMediumYellow.visibility = View.VISIBLE
            R.id.colorLightOrange -> binding.checkIconLightOrange.visibility = View.VISIBLE
            R.id.colorMediumOrange -> binding.checkIconMediumOrange.visibility = View.VISIBLE
            R.id.colorBrown -> binding.checkIconBrown.visibility = View.VISIBLE
            R.id.colorGrey -> binding.checkIconGrey.visibility = View.VISIBLE
            R.id.colorPink -> binding.checkIconPink.visibility = View.VISIBLE
        }
        selectedColor = when (view.id) {
            R.id.colorBlack -> colors[0]
            R.id.colorRed -> colors[1]
            R.id.colorViolet -> colors[2]
            R.id.colorOceanBlue -> colors[3]
            R.id.colorBlue -> colors[4]
            R.id.colorWaterBlue -> colors[5]
            R.id.colorWaterGreen -> colors[6]
            R.id.colorGreen -> colors[7]
            R.id.colorLightYellow -> colors[8]
            R.id.colorMediumYellow -> colors[9]
            R.id.colorLightOrange -> colors[10]
            R.id.colorMediumOrange -> colors[11]
            R.id.colorBrown -> colors[12]
            R.id.colorGrey -> colors[13]
            R.id.colorPink -> colors[14]
            else -> throw IllegalArgumentException("Invalid color resource ID")
        }
    }

    private fun hideAllCheckIcons() {
        binding.checkIconBlack.visibility = View.INVISIBLE
        binding.checkIconRed.visibility = View.INVISIBLE
        binding.checkIconViolet.visibility = View.INVISIBLE
        binding.checkIconOceanBlue.visibility = View.INVISIBLE
        binding.checkIconBlue.visibility = View.INVISIBLE
        binding.checkIconWaterBlue.visibility = View.INVISIBLE
        binding.checkIconWaterGreen.visibility = View.INVISIBLE
        binding.checkIconGreen.visibility = View.INVISIBLE
        binding.checkIconLightYellow.visibility = View.INVISIBLE
        binding.checkIconMediumYellow.visibility = View.INVISIBLE
        binding.checkIconLightOrange.visibility = View.INVISIBLE
        binding.checkIconMediumOrange.visibility = View.INVISIBLE
        binding.checkIconBrown.visibility = View.INVISIBLE
        binding.checkIconGrey.visibility = View.INVISIBLE
        binding.checkIconPink.visibility = View.INVISIBLE
    }
}