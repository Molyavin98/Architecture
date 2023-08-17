package com.molyavin.mvvm.domain.usecase

import com.molyavin.mvvm.data.repositories.SettingRepository
import javax.inject.Inject

class SetStatusRememberMeUseCase @Inject constructor(private val settingRepository: SettingRepository) : IUseCase.ISetStatusRememberMeUseCase{

    override fun execute(income: String) {
        settingRepository.saveSetting("RememberMeStatus", income)
    }
}