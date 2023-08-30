package com.molyavin.mvvm.presentation.controllers.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.molyavin.mvvm.R
import com.molyavin.mvvm.di.Injector
import com.molyavin.mvvm.presentation.ui.DefaultButton
import com.molyavin.mvvm.presentation.ui.DefaultImageLogo
import com.molyavin.mvvm.presentation.ui.DefaultText
import com.molyavin.mvvm.presentation.controllers.BaseViewController
import com.molyavin.mvvm.presentation.viewmodels.profile.ProfileViewModel
import com.molyavin.mvvm.presentation.ui.theme.MVVMTheme

class ProfileController : BaseViewController() {

    override val viewModel: ProfileViewModel = Injector.INSTANCE.provideProfileViewModel()

    @Composable
    override fun content() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            DefaultImageLogo(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .clip(CircleShape)
                    .border(
                        border = BorderStroke(
                            2.dp, color = colorResource(
                                id = R.color.black
                            )
                        ), shape = CircleShape
                    ),
                idImage = R.drawable.default_user_photo
            )

            DefaultText(
                modifier = Modifier
                    .padding(top = 20.dp),
                text = "Email: ${viewModel.userInfo.value.email}"
            )

            DefaultButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(16.dp)
                    .wrapContentHeight(align = Alignment.Bottom),
                text = "Exit from account",
                onClick = { viewModel.logOut() })

        }
    }

}