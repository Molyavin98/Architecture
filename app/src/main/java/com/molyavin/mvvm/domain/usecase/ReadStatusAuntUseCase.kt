package com.molyavin.mvvm.domain.usecase

import com.molyavin.mvvm.data.repositories.SettingRepository
import javax.inject.Inject

class ReadStatusAuntUseCase @Inject constructor(private val settingRepository: SettingRepository) :
    IUseCase.IReadStatusAuntUseCase {

    override fun execute(income: Any?): String {
        return settingRepository.readSetting("AuntStatus")
    }
}