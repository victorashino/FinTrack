package com.example.fintrack.domain.usecase

import com.example.fintrack.data.repository.ColorRepository

class SelectColorUseCase(private val colorRepository: ColorRepository) {

    fun execute(colorId: Int): Int {
        return colorRepository.getColorResourceById(colorId)
    }

    fun getAllColors(): Array<Int> {
        return colorRepository.colors
    }

}