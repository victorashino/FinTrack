package com.example.fintrack.domain.usecase

import com.example.fintrack.data.repository.IconRepository

class SelectIconUseCase(private val iconRepository: IconRepository) {

    fun execute(iconId: Int): Int {
        return iconRepository.getIconResourceById(iconId)
    }

    fun getAllIcons(): Array<Int> {
        return iconRepository.icons
    }

}