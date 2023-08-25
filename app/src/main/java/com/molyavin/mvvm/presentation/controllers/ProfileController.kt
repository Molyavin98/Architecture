package com.molyavin.mvvm.presentation.controllers

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.molyavin.mvvm.R
import com.molyavin.mvvm.domain.di.component.Injector
import com.molyavin.mvvm.presentation.DefaultButton
import com.molyavin.mvvm.presentation.DefaultImageLogo
import com.molyavin.mvvm.presentation.DefaultText
import com.molyavin.mvvm.presentation.viewmodels.ProfileViewModel
import com.molyavin.mvvm.presentation.ui.theme.MVVMTheme
import javax.inject.Singleton

class ProfileController : BaseViewController() {

    @Singleton
    override val viewModel: ProfileViewModel = Injector.INSTANCE.provideProfileViewModel()
    override fun setupView(view: ComposeView) {

        Injector.INSTANCE.inject(this)

        view.setContent {
            MVVMTheme {
                Scaffold {
                    Column(
                        modifier = Modifier
                            .padding(it)
                            .fillMaxSize(),
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

                        // загуглити про collectAsState
                        val userInfo = this@ProfileController.viewModel.userInfo.value

                        DefaultText(
                            modifier = Modifier
                                .padding(top = 20.dp),
                            text = "Full name: ${userInfo.fullName}"
                        )


                        DefaultText(
                            modifier = Modifier.padding(0.dp),
                            text = "Phone: ${userInfo.phone}"
                        )


                        DefaultText(
                            modifier = Modifier.padding(0.dp),
                            text = "Password: ${userInfo.password}"
                        )

                        DefaultButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(16.dp)
                                .wrapContentHeight(align = Alignment.Bottom),
                            text = "Exit from account",
                            onClick = { this@ProfileController.viewModel.logOut() })

                    }
                }
            }
        }

        this.viewModel.onCreate()
    }
}