package com.molyavin.mvvm.domain.mapper

import com.molyavin.mvvm.data.model.SlideDTO
import com.molyavin.mvvm.domain.models.SlideVM
import javax.inject.Inject

class SlideMapper @Inject constructor() {
    fun mapEntityToVM(slideEntity: SlideDTO): SlideVM {

        return SlideVM(
            idImage = slideEntity.idImage,
            title = slideEntity.title,
            description = slideEntity.description
        )
    }
}