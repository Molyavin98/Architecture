package com.molyavin.mvvm.presentation.viewmodels.search

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import com.bluelinelabs.conductor.Router
import com.molyavin.mvvm.domain.models.ProductVM
import com.molyavin.mvvm.domain.usecase.GetProductUseCase
import com.molyavin.mvvm.presentation.viewmodels.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase,
    router: Router
) : BaseViewModel(router) {

    private var _requestText = MutableStateFlow(TextFieldValue())
    val requestText: StateFlow<TextFieldValue> = _requestText

    private var _product = MutableStateFlow(ProductVM.empty())
    val product: StateFlow<ProductVM> = _product

    fun search() {

        viewModelScope.launch {

            getProductUseCase.execute(requestText.value.text).collect{productVM ->
                _product.value = productVM
            }
        }
    }

    fun setText(text: TextFieldValue) {
        _requestText.value = text
    }
}