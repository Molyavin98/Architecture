package com.molyavin.mvvm.domain.usecase.auth

import com.molyavin.mvvm.data.repositories.SettingRepository
import com.molyavin.mvvm.domain.usecase.base.IUseCase
import javax.inject.Inject

class SetStatusRememberMeUseCase @Inject constructor(private val settingRepository: SettingRepository) :
    IUseCase<Boolean, Unit> {

    override fun execute(income: Boolean) {
        settingRepository.saveSetting("RememberMe", income)
    }
}