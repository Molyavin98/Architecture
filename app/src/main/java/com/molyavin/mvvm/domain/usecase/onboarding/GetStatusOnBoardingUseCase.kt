package com.molyavin.mvvm.domain.usecase.onboarding

import com.molyavin.mvvm.data.repositories.SettingRepository
import com.molyavin.mvvm.domain.usecase.base.IAsyncUseCase
import com.molyavin.mvvm.utils.Constants
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetStatusOnBoardingUseCase @Inject constructor(
    private val settingRepository: SettingRepository,
    private val getRemoteConfigValueUseCase: GetRemoteConfigValueUseCase
) :
    IAsyncUseCase<Any?, Boolean> {

    override suspend fun execute(income: Any?): Boolean {

        val remoteConfigValueFlow =
            getRemoteConfigValueUseCase.execute(Constants.ON_BOARDING_REMOTE_KEY)

        if (remoteConfigValueFlow.first()) {
            return true
        }

        return settingRepository.readSetting(Constants.ON_BOARDING_LOCAL_KEY)
    }
}