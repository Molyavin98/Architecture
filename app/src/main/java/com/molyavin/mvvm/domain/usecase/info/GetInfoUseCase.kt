package com.molyavin.mvvm.domain.usecase.info

import com.molyavin.mvvm.data.model.InfoDTO
import com.molyavin.mvvm.data.repositories.InfoAppRepository
import com.molyavin.mvvm.domain.usecase.base.IUseCase
import javax.inject.Inject

class GetInfoUseCase @Inject constructor(private val infoAppRepository: InfoAppRepository) :
    IUseCase<Unit?, List<InfoDTO>> {

    override fun execute(income: Unit?): List<InfoDTO> {
        return infoAppRepository.getInfo()
    }
}