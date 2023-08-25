package com.molyavin.mvvm.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import com.bluelinelabs.conductor.Controller

abstract class BaseViewController : Controller() {

    protected abstract val viewModel: BaseViewModel
    protected abstract fun setupView(view: ComposeView)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup,
        savedViewState: Bundle?
    ): View {

        val view = viewModel.onCreateView(
            inflater = inflater,
            container = container,
            savedViewState = savedViewState
        )

        setupView(view as ComposeView)

        return view
    }
}