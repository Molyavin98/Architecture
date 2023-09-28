package com.molyavin.mvvm.presentation.controllers.main

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.molyavin.mvvm.R
import com.molyavin.mvvm.di.Injector
import com.molyavin.mvvm.presentation.controllers.BaseViewController
import com.molyavin.mvvm.presentation.ui.DefaultCenterAlignedTopAppBar
import com.molyavin.mvvm.presentation.ui.LazyList
import com.molyavin.mvvm.presentation.viewmodels.main.MenuViewModel

class MenuController : BaseViewController() {

    override val viewModel: MenuViewModel = Injector.INSTANCE.provideMenuViewModel()

    override fun onContextAvailable(context: Context) {
        viewModel.attachRoot(this)
    }

    @SuppressLint("SuspiciousIndentation")
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
                textTitle = "Menu",
                titleContentColor = Color.Black,
                textStyleTitle = MaterialTheme.typography.h3,
                navigationOnClick = viewModel::routeToUserProfile,
                navigationIcon = Icons.Filled.AccountCircle,
                navigationIconTint = Color.Black,
                containerColor = Color.White,
                actionOnClick = {}
            )

            val words = viewModel.wordsList.collectAsState().value


            LazyList(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                words = words,
                modifierItem = Modifier
                    .fillMaxWidth(),
                containerColor = R.color.image_color_in_menu,
                buttonModifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                btnDeleteText = "Delete",
                btnDeleteClick = { index -> viewModel.deleteWord(words[index].id.toString()) },
                btnEditText = "Edit",
                btnEditClick = { index ->
                    viewModel.editWord(index.toString())
                    viewModel.routerToEditWords()
                }
            )
        }


        FloatingActionButton(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .wrapContentWidth(align = Alignment.End)
                .wrapContentHeight(align = Alignment.Bottom)
                .padding(16.dp),
            onClick = { viewModel.routeToAddWords() },
            contentColor = Color.White,
            backgroundColor = colorResource(id = R.color.default_button_color)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = null
            )
        }
    }

}