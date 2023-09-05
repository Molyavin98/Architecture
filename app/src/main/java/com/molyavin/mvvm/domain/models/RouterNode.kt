package com.molyavin.mvvm.domain.models

import com.molyavin.mvvm.presentation.controllers.BaseViewController

data class RouterNode<T : BaseViewController>(val controllerClass: Class<T>)