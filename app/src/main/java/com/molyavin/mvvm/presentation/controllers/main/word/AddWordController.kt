package com.molyavin.mvvm.presentation.controllers.main.word

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.molyavin.mvvm.R
import com.molyavin.mvvm.di.Injector
import com.molyavin.mvvm.presentation.controllers.BaseViewController
import com.molyavin.mvvm.presentation.ui.DefaultButton
import com.molyavin.mvvm.presentation.ui.DefaultCenterAlignedTopAppBar
import com.molyavin.mvvm.presentation.ui.DefaultTextField
import com.molyavin.mvvm.presentation.viewmodels.main.word.AddWordViewModel

class AddWordController : BaseViewController() {

    override val viewModel: AddWordViewModel = Injector.INSTANCE.provideAddWordsViewModel()

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
                    .height(60.dp),
                textTitle = "Add words in storage",
                titleContentColor = Color.Black,
                textStyleTitle = MaterialTheme.typography.h3,
                navigationOnClick = { viewModel.navigateToBack() },
                navigationIcon = Icons.Filled.ArrowBack,
                navigationIconTint = Color.Black,
                containerColor = Color.White,
                actionIconTint = Color.Black,
                actionOnClick = {}
            )

            val engWord by viewModel.englishWord.collectAsState()
            val uaWord by viewModel.ukraineWord.collectAsState()

            DefaultTextField(
                value = engWord,
                onValueChange = { newWord -> viewModel.setEnglishWords(newWord) },
                hint = "English word",
                focusColor = R.color.default_border_focus_color,
                unFocusColor = R.color.default_border_color
            )

            DefaultTextField(
                value = uaWord,
                onValueChange = { newWord -> viewModel.setUkraineWords(newWord) },
                hint = "Ukraine word",
                focusColor = R.color.default_border_focus_color,
                unFocusColor = R.color.default_border_color
            )

            DefaultButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 32.dp, end = 16.dp),
                text = "Add",
                onClick = {
                    viewModel.addWord()
                })


        }
    }
}