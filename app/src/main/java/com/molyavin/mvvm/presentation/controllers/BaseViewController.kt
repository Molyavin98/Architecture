package com.molyavin.mvvm.presentation.controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import com.bluelinelabs.conductor.Controller
import com.molyavin.mvvm.presentation.viewmodels.BaseViewModel

abstract class BaseViewController : Controller() {

    protected abstract val viewModel: BaseViewModel
    protected abstract fun setupView(view: ComposeView)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup,
        savedViewState: Bundle?
    ): View {

        val view: ComposeView = ComposeView(container.context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }

        setupView(view = view)

        viewModel.onCreateView()

        return view
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        viewModel.onAttach()
    }
}