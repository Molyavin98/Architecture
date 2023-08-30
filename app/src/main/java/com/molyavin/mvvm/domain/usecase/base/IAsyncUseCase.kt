package com.molyavin.mvvm.domain.usecase.base

interface IAsyncUseCase<T, R> {

    suspend fun execute(income: T): R
}