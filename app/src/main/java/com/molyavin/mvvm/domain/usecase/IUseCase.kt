package com.molyavin.mvvm.domain.usecase

interface IUseCase<T, R> {
    fun execute(income: T): R
}