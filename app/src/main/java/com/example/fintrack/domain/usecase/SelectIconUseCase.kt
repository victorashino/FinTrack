package com.example.fintrack.domain.usecase

import com.example.fintrack.data.repository.IconRepository

class SelectIconUseCase(private val iconRepository: IconRepository) {

    fun getAllIcons(): Array<Int> {
        return iconRepository.icons
    }

}