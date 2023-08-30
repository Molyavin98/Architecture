package com.molyavin.mvvm.presentation.controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import com.bluelinelabs.conductor.Controller
import com.molyavin.mvvm.R
import com.molyavin.mvvm.presentation.ui.theme.MVVMTheme
import com.molyavin.mvvm.presentation.viewmodels.BaseViewModel

abstract class BaseViewController : Controller() {

    protected abstract val viewModel: BaseViewModel

    @Composable
    protected abstract fun content()

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

        viewModel.onCreateView()

        view.setContent {
            MVVMTheme {
                Scaffold {
                    Box(
                        modifier = Modifier
                            .padding(it)
                            .fillMaxSize()
                    ) {
                        val isLoading = viewModel.isLoading.value
                        if (isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center),
                                color = colorResource(id = R.color.default_button_color)
                            )
                        } else {
                            content()
                        }
                    }
                }
            }
        }

        return view
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        viewModel.onAttach()
    }

    override fun onDetach(view: View) {
        super.onDetach(view)
        viewModel.onDetach()
    }

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)
        viewModel.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }
}