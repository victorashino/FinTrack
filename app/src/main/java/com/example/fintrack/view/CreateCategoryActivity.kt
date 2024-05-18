package com.example.fintrack.view

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fintrack.R
import com.example.fintrack.databinding.ActivityCreateCategoryBinding
import com.example.fintrack.utils.AppUtils
import com.example.fintrack.view.viewmodel.CreateCategoryViewModel

class CreateCategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateCategoryBinding

    private val viewModel: CreateCategoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCreateCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val colors = arrayOf(
            R.color.black,
            R.color.white,
            R.color.red,
            R.color.violet,
            R.color.ocean_blue,
            R.color.blue,
            R.color.water_blue,
            R.color.water_green,
            R.color.light_green,
            R.color.light_yellow,
            R.color.medium_yellow,
            R.color.light_orange,
            R.color.medium_orange,
            R.color.light_brown,
            R.color.grey,
            R.color.pink,
            R.color.medium_green,
            R.color.light_brown
        )

        val xmlColorList = listOf(
            binding.colorBlack,
            binding.colorWhite,
            binding.colorRed,
            binding.colorViolet,
            binding.colorOceanBlue,
            binding.colorBlue,
            binding.colorWaterBlue,
            binding.colorWaterGreen,
            binding.colorGreen,
            binding.colorLightYellow,
            binding.colorMediumYellow,
            binding.colorLightOrange,
            binding.colorMediumOrange,
            binding.colorLightBrown,
            binding.colorGrey,
            binding.colorPink,
            binding.colorMediumGreen,
            binding.colorMediumBrown
        )

        val checkIconList = listOf(
            binding.checkIconBlack,
            binding.checkIconWhite,
            binding.checkIconRed,
            binding.checkIconViolet,
            binding.checkIconOceanBlue,
            binding.checkIconBlue,
            binding.checkIconWaterBlue,
            binding.checkIconWaterGreen,
            binding.checkIconGreen,
            binding.checkIconLightYellow,
            binding.checkIconMediumYellow,
            binding.checkIconLightOrange,
            binding.checkIconMediumOrange,
            binding.checkIconBrown,
            binding.checkIconGrey,
            binding.checkIconPink,
            binding.checkIconMediumGreen,
            binding.checkIconMediumBrown
        )

        for (color in xmlColorList) {
            color.setOnClickListener { viewModel.onColorClicked(checkIconList, colors, color) }
        }

        binding.icBack.setOnClickListener {
            AppUtils.back(this)
        }

    }


    fun onIconClicked(view: View) {}
}