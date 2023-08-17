package com.molyavin.mvvm.domain.usecase

import com.molyavin.mvvm.data.repositories.SettingRepository
import javax.inject.Inject

class GetStatusRememberMeUseCase @Inject constructor(private val settingRepository: SettingRepository) :
    IUseCase.IGetStatusRememberMeUseCase {
    override fun execute(income: Any?): String {
        return settingRepository.readSetting("RememberMeStatus")
    }
}