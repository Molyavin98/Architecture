package com.molyavin.mvvm.domain.usecase

import com.molyavin.mvvm.domain.models.SlideEntity
import com.molyavin.mvvm.domain.models.SlideVM

class SlideMapper {
    fun mapEntityToVM(slideEntity: SlideEntity): SlideVM {

        return SlideVM(
            idImage = slideEntity.idImage,
            title = slideEntity.title,
            description = slideEntity.description
        )
    }
}