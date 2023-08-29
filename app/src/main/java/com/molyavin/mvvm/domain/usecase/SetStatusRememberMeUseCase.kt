package com.molyavin.mvvm.domain.usecase

import com.molyavin.mvvm.data.repositories.SettingRepository
import javax.inject.Inject

class SetStatusRememberMeUseCase @Inject constructor(private val settingRepository: SettingRepository) :
    IUseCase<Boolean,Unit> {

    override fun execute(income: Boolean) {
        settingRepository.saveSetting("RememberMe", income)
    }
}