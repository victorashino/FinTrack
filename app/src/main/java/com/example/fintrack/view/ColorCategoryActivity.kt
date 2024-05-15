package com.example.fintrack.view

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fintrack.R
import com.example.fintrack.databinding.ActivityColorCategoryBinding

class ColorCategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityColorCategoryBinding

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

        onColorClicked(binding.colorBlack)
        onColorClicked(binding.colorRed)
        onColorClicked(binding.colorViolet)
        onColorClicked(binding.colorOceanBlue)
        onColorClicked(binding.colorBlue)
        onColorClicked(binding.colorWaterBlue)
        onColorClicked(binding.colorWaterGreen)
        onColorClicked(binding.colorGreen)
        onColorClicked(binding.colorLightYellow)
        onColorClicked(binding.colorMediumYellow)
        onColorClicked(binding.colorLightOrange)
        onColorClicked(binding.colorMediumOrange)
        onColorClicked(binding.colorBrown)
        onColorClicked(binding.colorGrey)
        onColorClicked(binding.colorPink)

        binding.icBack.setOnClickListener {
            finish()
        }
    }

    fun onColorClicked(v: View) {
        v.setOnClickListener {
            when (v.id
            ) {
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
        }

    }
}