package com.example.fintrack.ui.createcategory

import android.content.Context
import android.os.Build
import android.view.View
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fintrack.R
import com.example.fintrack.domain.usecase.SelectColorUseCase
import com.example.fintrack.domain.usecase.SelectIconUseCase

class CreateCategoryViewModel(
    selectColorUseCase: SelectColorUseCase,
    selectIconUseCase: SelectIconUseCase
    ) : ViewModel() {

    var selectedColor: Int? = null
    var selectedIcon: Int? = null

    private val colors: Array<Int> = selectColorUseCase.getAllColors()
    private val icons: Array<Int> = selectIconUseCase.getAllIcons()

    fun onColorClicked(checkIconList: List<ImageView>, colorViewList: List<View>, view: View) {
        hideAllCheckIcons(checkIconList)
        when (view.id) {
            colorViewList[0].id -> checkIconList[0].visibility = View.VISIBLE
            colorViewList[1].id  -> checkIconList[1].visibility = View.VISIBLE
            colorViewList[2].id  -> checkIconList[2].visibility = View.VISIBLE
            colorViewList[3].id  -> checkIconList[3].visibility = View.VISIBLE
            colorViewList[4].id  -> checkIconList[4].visibility = View.VISIBLE
            colorViewList[5].id  -> checkIconList[5].visibility = View.VISIBLE
            colorViewList[6].id  -> checkIconList[6].visibility = View.VISIBLE
            colorViewList[7].id  -> checkIconList[7].visibility = View.VISIBLE
            colorViewList[8].id  -> checkIconList[8].visibility = View.VISIBLE
            colorViewList[9].id -> checkIconList[9].visibility = View.VISIBLE
            colorViewList[10].id  -> checkIconList[10].visibility = View.VISIBLE
            colorViewList[11].id  -> checkIconList[11].visibility = View.VISIBLE
            colorViewList[12].id  -> checkIconList[12].visibility = View.VISIBLE
            colorViewList[13].id  -> checkIconList[13].visibility = View.VISIBLE
            colorViewList[14].id  -> checkIconList[14].visibility = View.VISIBLE
            colorViewList[15].id  -> checkIconList[15].visibility = View.VISIBLE
            colorViewList[16].id  -> checkIconList[16].visibility = View.VISIBLE
            colorViewList[17].id  -> checkIconList[17].visibility = View.VISIBLE
        }
        selectedColor = when (view.id) {
            colorViewList[0].id -> colors[0]
            colorViewList[1].id -> colors[1]
            colorViewList[2].id -> colors[2]
            colorViewList[3].id -> colors[3]
            colorViewList[4].id -> colors[4]
            colorViewList[5].id -> colors[5]
            colorViewList[6].id -> colors[6]
            colorViewList[7].id -> colors[7]
            colorViewList[8].id -> colors[8]
            colorViewList[9].id -> colors[9]
            colorViewList[10].id -> colors[10]
            colorViewList[11].id -> colors[11]
            colorViewList[12].id -> colors[12]
            colorViewList[13].id -> colors[13]
            colorViewList[14].id -> colors[14]
            colorViewList[15].id -> colors[15]
            colorViewList[16].id -> colors[16]
            colorViewList[17].id -> colors[17]
            else -> throw IllegalArgumentException("Invalid color resource ID")
        }
    }

    private fun hideAllCheckIcons(checkList: List<ImageView>) {
        for (check in checkList) {
            check.visibility = View.INVISIBLE
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun onIconClicked(context: Context, icon: ImageView, iconViewLis: List<ImageView>, backgroundDark: Int) {
        setBackgroundDefault(iconViewLis)

        val selectedColorIcon = ContextCompat.getColor(context, colors[1])
        when (icon.id) {
            iconViewLis[0].id -> { iconViewLis[0].setBackgroundResource(backgroundDark); iconViewLis[0].setColorFilter(selectedColorIcon) }
            iconViewLis[1].id -> { iconViewLis[1].setBackgroundResource(backgroundDark); iconViewLis[1].setColorFilter(selectedColorIcon) }
            iconViewLis[2].id -> { iconViewLis[2].setBackgroundResource(backgroundDark); iconViewLis[2].setColorFilter(selectedColorIcon) }
            iconViewLis[3].id -> { iconViewLis[3].setBackgroundResource(backgroundDark); iconViewLis[3].setColorFilter(selectedColorIcon) }
            iconViewLis[4].id -> { iconViewLis[4].setBackgroundResource(backgroundDark); iconViewLis[4].setColorFilter(selectedColorIcon) }
            iconViewLis[5].id -> { iconViewLis[5].setBackgroundResource(backgroundDark); iconViewLis[5].setColorFilter(selectedColorIcon) }
            iconViewLis[6].id -> { iconViewLis[6].setBackgroundResource(backgroundDark); iconViewLis[6].setColorFilter(selectedColorIcon) }
            iconViewLis[7].id -> { iconViewLis[7].setBackgroundResource(backgroundDark); iconViewLis[7].setColorFilter(selectedColorIcon) }
            iconViewLis[8].id -> { iconViewLis[8].setBackgroundResource(backgroundDark); iconViewLis[8].setColorFilter(selectedColorIcon) }
            iconViewLis[9].id -> { iconViewLis[9].setBackgroundResource(backgroundDark); iconViewLis[9].setColorFilter(selectedColorIcon) }
            iconViewLis[10].id -> { iconViewLis[10].setBackgroundResource(backgroundDark); iconViewLis[10].setColorFilter(selectedColorIcon) }
            iconViewLis[11].id -> { iconViewLis[11].setBackgroundResource(backgroundDark); iconViewLis[11].setColorFilter(selectedColorIcon) }
            iconViewLis[12].id -> { iconViewLis[12].setBackgroundResource(backgroundDark); iconViewLis[12].setColorFilter(selectedColorIcon) }
            iconViewLis[13].id -> { iconViewLis[13].setBackgroundResource(backgroundDark); iconViewLis[13].setColorFilter(selectedColorIcon) }
            iconViewLis[14].id -> { iconViewLis[14].setBackgroundResource(backgroundDark); iconViewLis[14].setColorFilter(selectedColorIcon) }
            iconViewLis[15].id -> { iconViewLis[15].setBackgroundResource(backgroundDark); iconViewLis[15].setColorFilter(selectedColorIcon) }
            iconViewLis[16].id -> { iconViewLis[16].setBackgroundResource(backgroundDark); iconViewLis[16].setColorFilter(selectedColorIcon) }
            iconViewLis[17].id -> { iconViewLis[17].setBackgroundResource(backgroundDark); iconViewLis[17].setColorFilter(selectedColorIcon) }
        }
        selectedIcon = when (icon.id) {
            iconViewLis[0].id -> icons[0]
            iconViewLis[1].id -> icons[1]
            iconViewLis[2].id -> icons[2]
            iconViewLis[3].id -> icons[3]
            iconViewLis[4].id -> icons[4]
            iconViewLis[5].id -> icons[5]
            iconViewLis[6].id -> icons[6]
            iconViewLis[7].id -> icons[7]
            iconViewLis[8].id -> icons[8]
            iconViewLis[9].id -> icons[9]
            iconViewLis[10].id -> icons[10]
            iconViewLis[11].id -> icons[11]
            iconViewLis[12].id -> icons[12]
            iconViewLis[13].id -> icons[13]
            iconViewLis[14].id -> icons[14]
            iconViewLis[15].id -> icons[15]
            iconViewLis[16].id -> icons[16]
            iconViewLis[17].id -> icons[17]
            else -> throw IllegalArgumentException("Invalid color resource ID")
        }
    }

    private fun setBackgroundDefault(iconViewLis: List<ImageView>) {
        for (icon in iconViewLis) {
            icon.setBackgroundResource(R.drawable.icon_background_white)
            icon.setColorFilter(ContextCompat.getColor(icon.context, colors[0]))
        }
    }

    companion object {
        fun provideFactory(
            selectColorUseCase: SelectColorUseCase,
            selectIconUseCase: SelectIconUseCase
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(CreateCategoryViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return CreateCategoryViewModel(selectColorUseCase, selectIconUseCase) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }

}