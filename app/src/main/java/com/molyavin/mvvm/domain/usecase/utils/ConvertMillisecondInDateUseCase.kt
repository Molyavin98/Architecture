package com.molyavin.mvvm.domain.usecase.utils

import com.molyavin.mvvm.domain.usecase.base.IAsyncUseCase
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class ConvertMillisecondInDateUseCase @Inject constructor() :
    IAsyncUseCase<Long, String> {

    override suspend fun execute(income: Long): String {
        val date = Date(income)
        val format = SimpleDateFormat("dd MMM yyyy", Locale.UK)
        return format.format(date)
    }

}