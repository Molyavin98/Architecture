package com.molyavin.mvvm.domain.usecase.base

interface IUseCase<T, R> {
    fun execute(income: T): R
}