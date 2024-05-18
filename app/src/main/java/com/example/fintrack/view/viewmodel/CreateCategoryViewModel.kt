package com.example.fintrack.view.viewmodel

import android.view.View
import android.widget.ImageView
import androidx.lifecycle.ViewModel
import com.example.fintrack.R

class CreateCategoryViewModel : ViewModel() {

    private var selectedColor: Int? = null

    fun onColorClicked(checkIconList: List<ImageView>, colors: Array<Int>, view: View) {

        hideAllCheckIcons(checkIconList)

        when (view.id) {
            R.id.colorBlack -> checkIconList[0].visibility = View.VISIBLE
            R.id.colorWhite -> checkIconList[1].visibility = View.VISIBLE
            R.id.colorRed -> checkIconList[2].visibility = View.VISIBLE
            R.id.colorViolet -> checkIconList[3].visibility = View.VISIBLE
            R.id.colorOceanBlue -> checkIconList[4].visibility = View.VISIBLE
            R.id.colorBlue -> checkIconList[5].visibility = View.VISIBLE
            R.id.colorWaterBlue -> checkIconList[6].visibility = View.VISIBLE
            R.id.colorWaterGreen -> checkIconList[7].visibility = View.VISIBLE
            R.id.colorGreen -> checkIconList[8].visibility = View.VISIBLE
            R.id.colorLightYellow -> checkIconList[9].visibility = View.VISIBLE
            R.id.colorMediumYellow -> checkIconList[10].visibility = View.VISIBLE
            R.id.colorLightOrange -> checkIconList[11].visibility = View.VISIBLE
            R.id.colorMediumOrange -> checkIconList[12].visibility = View.VISIBLE
            R.id.colorLightBrown -> checkIconList[13].visibility = View.VISIBLE
            R.id.colorGrey -> checkIconList[14].visibility = View.VISIBLE
            R.id.colorPink -> checkIconList[15].visibility = View.VISIBLE
            R.id.colorMediumGreen -> checkIconList[16].visibility = View.VISIBLE
            R.id.colorMediumBrown -> checkIconList[17].visibility = View.VISIBLE
        }
        selectedColor = when (view.id) {
            R.id.colorBlack -> colors[0]
            R.id.colorWhite -> colors[1]
            R.id.colorRed -> colors[2]
            R.id.colorViolet -> colors[3]
            R.id.colorOceanBlue -> colors[4]
            R.id.colorBlue -> colors[5]
            R.id.colorWaterBlue -> colors[6]
            R.id.colorWaterGreen -> colors[7]
            R.id.colorGreen -> colors[8]
            R.id.colorLightYellow -> colors[9]
            R.id.colorMediumYellow -> colors[10]
            R.id.colorLightOrange -> colors[11]
            R.id.colorMediumOrange -> colors[12]
            R.id.colorLightBrown -> colors[13]
            R.id.colorGrey -> colors[14]
            R.id.colorPink -> colors[15]
            R.id.colorMediumGreen -> colors[16]
            R.id.colorMediumBrown -> colors[17]
            else -> throw IllegalArgumentException("Invalid color resource ID")
        }
    }

    private fun hideAllCheckIcons(checkList: List<ImageView>) {
        for (check in checkList) {
            check.visibility = View.INVISIBLE
        }
    }

}