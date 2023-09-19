package com.molyavin.mvvm.domain.usecase.sharedpref

import com.molyavin.mvvm.data.repositories.WordRepository
import com.molyavin.mvvm.data.storage.DBSharedPreference
import com.molyavin.mvvm.domain.usecase.base.IUseCase
import com.molyavin.mvvm.utils.Constants.USER_VM_KEY
import javax.inject.Inject

class GetWordVMUseCase @Inject constructor(private val wordRepository: WordRepository) :
    IUseCase<Any?, Int> {

    override fun execute(income: Any?): Int {
        return wordRepository.getID()
    }
}