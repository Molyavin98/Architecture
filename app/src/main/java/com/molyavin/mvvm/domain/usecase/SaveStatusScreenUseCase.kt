package com.molyavin.mvvm.domain.usecase

import com.molyavin.mvvm.data.repositories.OnBoardingRepository
import javax.inject.Inject

class SaveStatusScreenUseCase @Inject constructor(private val onBoardingRepository: OnBoardingRepository) :
    IUseCase.ISaveStatusScreenUseCase {
    override fun execute(income: String) {
        onBoardingRepository.saveStatus(income)
    }


}