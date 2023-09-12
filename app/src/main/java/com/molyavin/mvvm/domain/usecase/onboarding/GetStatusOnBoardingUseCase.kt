package com.molyavin.mvvm.domain.usecase.onboarding

import android.annotation.SuppressLint
import com.molyavin.mvvm.data.repositories.SettingRepository
import com.molyavin.mvvm.domain.usecase.base.IUseCase
import com.molyavin.mvvm.utils.AppDispatchers
import com.molyavin.mvvm.utils.Constants
import io.reactivex.Single
import javax.inject.Inject

class GetStatusOnBoardingUseCase @Inject constructor(
    private val settingRepository: SettingRepository,
    private val getRemoteConfigValueUseCase: GetRemoteConfigValueUseCase,
    private val dispatchers: AppDispatchers,
) :
    IUseCase<Any?, Single<Boolean>> {

    @SuppressLint("CheckResult")
    override fun execute(income: Any?): Single<Boolean> {
        return getRemoteConfigValueUseCase.execute(Constants.ON_BOARDING_REMOTE_KEY)
            .map { it && settingRepository.readSetting(Constants.ON_BOARDING_LOCAL_KEY, true) }
            .subscribeOn(dispatchers.rxIo)
    }
}
