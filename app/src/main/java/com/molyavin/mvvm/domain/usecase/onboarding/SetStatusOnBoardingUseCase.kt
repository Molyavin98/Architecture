package com.molyavin.mvvm.domain.usecase.onboarding

import com.molyavin.mvvm.data.repositories.SettingRepository
import com.molyavin.mvvm.domain.usecase.base.IUseCase
import javax.inject.Inject

class SetStatusOnBoardingUseCase @Inject constructor(private val settingRepository: SettingRepository) :
    IUseCase<Boolean, Unit> {
    override fun execute(income: Boolean) {
        settingRepository.saveSetting("OnBoarding", income)
    }

}