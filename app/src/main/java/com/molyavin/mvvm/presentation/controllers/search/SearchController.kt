package com.molyavin.mvvm.presentation.controllers.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.molyavin.mvvm.R
import com.molyavin.mvvm.di.Injector
import com.molyavin.mvvm.presentation.controllers.BaseViewController
import com.molyavin.mvvm.presentation.ui.DefaultButton
import com.molyavin.mvvm.presentation.ui.DefaultCenterAlignedTopAppBar
import com.molyavin.mvvm.presentation.ui.DefaultGlideImage
import com.molyavin.mvvm.presentation.ui.SearchTextField
import com.molyavin.mvvm.presentation.viewmodels.search.SearchViewModel

class SearchController : BaseViewController() {

    override val viewModel: SearchViewModel = Injector.INSTANCE.provideSearchViewModel()

    @Composable
    override fun content() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {

            DefaultCenterAlignedTopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                textTitle = "Search",
                titleContentColor = Color.Black,
                textStyleTitle = MaterialTheme.typography.h2,
                navigationOnClick = { viewModel.navigateToBack() },
                navigationIcon = Icons.Filled.ArrowBack,
                navigationIconTint = Color.Black,
                containerColor = Color.White
            )

            val request by viewModel.requestText.collectAsState()
            val product by viewModel.product.collectAsState()


            SearchTextField(
                modifierText = Modifier
                    .padding(3.dp)
                    .weight(50f),
                text = request,
                onValueChange = { newText -> viewModel.setText(newText) },
                hint = "Enter num in range 1..100",
                focusColor = R.color.default_border_focus_color,
                unFocusColor = R.color.default_border_color,
            )

            DefaultButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 32.dp, end = 16.dp),
                text = "Search",
                onClick = { viewModel.search() },
            )

            DefaultGlideImage(
                modifier = Modifier
                    .size(200.dp)
                    .padding(16.dp),
                contentScale = ContentScale.Fit,
                urlImage = product.images[0]
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 32.dp, end = 16.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h4,
                color = colorResource(id = R.color.black),
                text = product.brand
            )
        }
    }


}