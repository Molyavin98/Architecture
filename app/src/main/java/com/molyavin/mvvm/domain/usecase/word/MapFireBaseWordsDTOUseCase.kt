package com.molyavin.mvvm.domain.usecase.word

import com.molyavin.mvvm.data.network.ApiServiceRetrofit
import com.molyavin.mvvm.domain.models.WordVM
import com.molyavin.mvvm.domain.usecase.base.IAsyncUseCase
import com.molyavin.mvvm.utils.WhileReceivingDataException
import javax.inject.Inject

class MapFireBaseWordsDTOUseCase @Inject constructor(private val apiServiceRetrofit: ApiServiceRetrofit) :
    IAsyncUseCase<Any?, List<WordVM>?> {

    override suspend fun execute(income: Any?): List<WordVM> {

        val response = apiServiceRetrofit.getWord()

        if (response.isSuccessful) {
            val wordsResponse = response.body()
            wordsResponse?.let { map ->
                val wordVMList = mutableListOf<WordVM>()

                for ((key, value) in map) {
                    val wordVM = WordVM(
                        id = key,
                        eng = value.eng,
                        ua = value.ua
                    )
                    wordVMList.add(wordVM)
                }
                return wordVMList
            }
        }
        throw WhileReceivingDataException()
    }
}