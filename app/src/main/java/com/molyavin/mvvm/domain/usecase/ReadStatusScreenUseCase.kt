package com.molyavin.mvvm.domain.usecase

import com.molyavin.mvvm.data.repositories.OnBoardingRepository
import javax.inject.Inject

class ReadStatusScreenUseCase @Inject constructor (val onBoardingRepository: OnBoardingRepository) :
    IUseCase.IReadStatusScreenUseCase {

    override fun execute(income: Any?): String {
        return onBoardingRepository.readStatus()
    }
}