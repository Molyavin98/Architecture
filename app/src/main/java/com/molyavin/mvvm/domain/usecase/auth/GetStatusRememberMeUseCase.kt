package com.molyavin.mvvm.domain.usecase.auth

import com.molyavin.mvvm.data.repositories.SettingRepository
import com.molyavin.mvvm.domain.usecase.base.IUseCase
import javax.inject.Inject

class GetStatusRememberMeUseCase @Inject constructor(private val settingRepository: SettingRepository) :
    IUseCase<Any?, Boolean> {
    override fun execute(income: Any?): Boolean {
        return settingRepository.readSetting("RememberMe")
    }
}