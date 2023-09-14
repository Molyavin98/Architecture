package com.molyavin.mvvm.domain.usecase

import com.molyavin.mvvm.data.network.ApiServiceRetrofit
import com.molyavin.mvvm.domain.models.ProductVM
import com.molyavin.mvvm.domain.models.toVM
import com.molyavin.mvvm.domain.usecase.base.IAsyncUseCase
import com.molyavin.mvvm.utils.AppDispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Retrofit
import java.util.concurrent.CancellationException
import javax.inject.Inject

class GetProductUseCase @Inject constructor(
    private val retrofit: Retrofit,
    private val dispatchers: AppDispatchers
) : IAsyncUseCase<String, Flow<ProductVM>> {

    override suspend fun execute(income: String): Flow<ProductVM> = flow {
        try {
            val productApi = retrofit.create(ApiServiceRetrofit::class.java)
            val productDTO = productApi.getProduct(income)
            emit(productDTO.toVM())
        } catch (e: Exception) {
            emit(ProductVM.empty())
            currentCoroutineContext().cancel(CancellationException())
        }
    }.flowOn(dispatchers.io)

}