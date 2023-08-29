package com.molyavin.mvvm.domain.usecase

interface IAsyncUseCase<T, R> {

    suspend fun execute(income: T): R
}