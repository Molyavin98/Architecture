package com.molyavin.mvvm.domain.usecase.onboarding

import com.molyavin.mvvm.data.repositories.SettingRepository
import com.molyavin.mvvm.domain.usecase.base.IUseCase
import javax.inject.Inject

class GetStatusOnBoardingUseCase @Inject constructor(private val settingRepository: SettingRepository) :
    IUseCase<Any?, Boolean> {
    override fun execute(income: Any?): Boolean {
        return settingRepository.readSetting("OnBoarding")
    }
}