package com.example.fintrack.ui.createcategory

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.fintrack.R
import com.example.fintrack.data.model.Category
import com.example.fintrack.data.repository.ColorRepository
import com.example.fintrack.data.repository.IconRepository
import com.example.fintrack.databinding.ActivityCreateCategoryBinding
import com.example.fintrack.domain.usecase.SelectColorUseCase
import com.example.fintrack.domain.usecase.SelectIconUseCase
import com.example.fintrack.ui.main.MainActivity
import com.example.fintrack.utils.AppUtils

class CreateCategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateCategoryBinding

    private lateinit var viewModel: CreateCategoryViewModel

    @RequiresApi(Build.VERSION_CODES.M)
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
        binding.icBack.setOnClickListener {
            AppUtils.back(this)
        }

        val selectColorUseCase = SelectColorUseCase(ColorRepository(this))
        val selectIconUseCase = SelectIconUseCase(IconRepository(this))
        val factory = CreateCategoryViewModel.provideFactory(selectColorUseCase, selectIconUseCase)
        viewModel = ViewModelProvider(this, factory)[CreateCategoryViewModel::class.java]

        val colorViewList = listOf(
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
        for (colorView in colorViewList) {
            colorView.setOnClickListener { viewModel.onColorClicked(checkIconList, colorViewList, colorView) }
        }

        val iconView = listOf(
            binding.iconHome,
            binding.iconCar,
            binding.iconKey,
            binding.iconGameControl,
            binding.iconWifi,
            binding.iconClothes,
            binding.iconCreditCard,
            binding.iconEnergy,
            binding.iconGasStation,
            binding.iconGraphic,
            binding.iconShoppingCart,
            binding.iconWaterDrop
        )
        for (icon in iconView) {
            icon.setOnClickListener { viewModel.onIconClicked(this, icon, iconView, R.drawable.icon_background_black) }
        }

        binding.btnSave.setOnClickListener {
            val color = viewModel.selectedColor
            val icon = viewModel.selectedIcon
            val name = binding.edtCategoryName.text.toString()
            if (color != null && icon != null && name.isNotEmpty()) {
                // TODO: Save category to database
                startActivity(MainActivity.startedByCreateCategory(this, 0, name, color, icon))
            }
        }

    }
}

