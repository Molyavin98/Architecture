package com.molyavin.mvvm.domain.usecase

import com.molyavin.mvvm.data.repositories.SettingRepository
import javax.inject.Inject

class GetStatusOnBoardingUseCase @Inject constructor(private val settingRepository: SettingRepository) :
    IUseCase<Any?, Boolean> {
    override fun execute(income: Any?): Boolean {
        return settingRepository.readSetting("OnBoarding")
    }
}