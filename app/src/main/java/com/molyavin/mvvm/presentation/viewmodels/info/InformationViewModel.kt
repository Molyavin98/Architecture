package com.molyavin.mvvm.presentation.viewmodels.info

import com.bluelinelabs.conductor.Router
import com.molyavin.mvvm.data.model.InfoDTO
import com.molyavin.mvvm.domain.usecase.info.GetInfoUseCase
import com.molyavin.mvvm.presentation.viewmodels.BaseViewModel
import com.molyavin.mvvm.utils.Toaster
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class InformationViewModel @Inject constructor(
    private val getInfoUseCase: GetInfoUseCase,
    router: Router,
    toaster: Toaster
) : BaseViewModel(router, toaster) {


    private var infoList = MutableStateFlow(emptyList<InfoDTO>())
    val _infoList: StateFlow<List<InfoDTO>> = infoList

    fun loadingInfoApp() {
        infoList.value = getInfoUseCase.execute(null)
    }

}