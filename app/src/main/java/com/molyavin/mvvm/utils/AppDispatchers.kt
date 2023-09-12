package com.molyavin.mvvm.utils

import com.bumptech.glide.util.Executors
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher

class AppDispatchers(
    val main: MainCoroutineDispatcher = Dispatchers.Main,
    val default: CoroutineDispatcher = Dispatchers.Default,
    val io: CoroutineDispatcher = Dispatchers.IO,
    val unconfined: CoroutineDispatcher = Dispatchers.Unconfined,

    val rxIo: Scheduler = Schedulers.io(),
    val rxDefault: Scheduler = Schedulers.computation(),
    val rxMain: Scheduler = AndroidSchedulers.mainThread(),
)