package com.molyavin.mvvm.domain.usecase

import com.molyavin.mvvm.data.repositories.SettingRepository
import javax.inject.Inject

class SaveStatusScreenUseCase @Inject constructor(private val settingRepository: SettingRepository) :
    IUseCase.ISaveStatusScreenUseCase {
    override fun execute(income: String) {
        settingRepository.saveSetting("Status", income)
    }

}