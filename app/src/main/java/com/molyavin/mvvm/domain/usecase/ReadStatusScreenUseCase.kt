package com.molyavin.mvvm.domain.usecase

import com.molyavin.mvvm.data.repositories.SettingRepository
import javax.inject.Inject

class ReadStatusScreenUseCase @Inject constructor (private val settingRepository: SettingRepository) :
    IUseCase.IReadStatusScreenUseCase {

    override fun execute(income: Any?): String {
        return settingRepository.readSetting("Status")
    }
}