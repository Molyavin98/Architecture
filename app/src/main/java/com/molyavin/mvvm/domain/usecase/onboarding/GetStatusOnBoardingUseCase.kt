package com.molyavin.mvvm.domain.usecase.onboarding

import com.molyavin.mvvm.data.repositories.SettingRepository
import com.molyavin.mvvm.domain.usecase.base.IAsyncUseCase
import javax.inject.Inject

class GetStatusOnBoardingUseCase @Inject constructor(
    private val settingRepository: SettingRepository,
    private val getRemoteConfigValueUseCase: GetRemoteConfigValueUseCase
) :
    IAsyncUseCase<Any?, Boolean> {

    override suspend fun execute(income: Any?): Boolean {

        if (getRemoteConfigValueUseCase.execute("on_boarding")) {
            return true
        }

        return settingRepository.readSetting("OnBoarding")
    }
}