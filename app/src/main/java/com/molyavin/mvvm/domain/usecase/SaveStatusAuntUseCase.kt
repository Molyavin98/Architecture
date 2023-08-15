package com.molyavin.mvvm.domain.usecase

import com.molyavin.mvvm.data.repositories.SettingRepository
import javax.inject.Inject

class SaveStatusAuntUseCase @Inject constructor(private val settingRepository: SettingRepository) :
    IUseCase.ISaveStatusAuntUseCase {


    override fun execute(income: String) {
        settingRepository.saveSetting("AuntStatus", income)
    }
}