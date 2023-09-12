package com.molyavin.mvvm.domain.usecase.auth

import com.molyavin.mvvm.data.repositories.SettingRepository
import com.molyavin.mvvm.domain.usecase.base.IUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetStatusRememberMeUseCase @Inject constructor(private val settingRepository: SettingRepository) :
    IUseCase<Any?, Single<Boolean>> {
    override fun execute(income: Any?): Single<Boolean> {
        return Single.just(settingRepository.readSetting("RememberMe", false))
    }
}