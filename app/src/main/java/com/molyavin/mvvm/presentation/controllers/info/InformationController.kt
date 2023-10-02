package com.molyavin.mvvm.presentation.controllers.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.compose.runtime.Composable
import com.airbnb.epoxy.EpoxyRecyclerView
import com.molyavin.mvvm.R
import com.molyavin.mvvm.di.Injector
import com.molyavin.mvvm.presentation.controllers.BaseViewController
import com.molyavin.mvvm.presentation.viewmodels.info.InformationViewModel

class InformationController : BaseViewController() {

    override val viewModel: InformationViewModel = Injector.INSTANCE.provideInformationViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup,
        savedViewState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.info, container, false).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }

        val recyclerView: EpoxyRecyclerView = view.findViewById(R.id.recyclerView)
        val btnBack: ImageButton = view.findViewById(R.id.buttonBack)
        val controllerEpoxy = InfoEpoxyController()

        viewModel.loadingInfoApp()
        controllerEpoxy.items = viewModel._infoList.value
        controllerEpoxy.requestModelBuild()

        recyclerView.setController(controllerEpoxy)
        btnBack.setOnClickListener { viewModel.navigateToBack() }

        return view
    }


    @Composable
    override fun content() {
    }


}