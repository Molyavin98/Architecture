package com.molyavin.mvvm.presentation.controllers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.molyavin.mvvm.R
import com.molyavin.mvvm.domain.di.component.Injector
import com.molyavin.mvvm.presentation.AuthFooter
import com.molyavin.mvvm.presentation.DefaultButton
import com.molyavin.mvvm.presentation.DefaultImageLogo
import com.molyavin.mvvm.presentation.DefaultPasswordField
import com.molyavin.mvvm.presentation.DefaultPhoneField
import com.molyavin.mvvm.presentation.DefaultSocialAuthButton
import com.molyavin.mvvm.presentation.DefaultText
import com.molyavin.mvvm.presentation.DividerOr
import com.molyavin.mvvm.presentation.viewmodels.RegistrationViewModel
import com.molyavin.mvvm.presentation.ui.theme.MVVMTheme
import javax.inject.Singleton

class RegistrationController : BaseViewController() {


    override lateinit var viewModel: RegistrationViewModel
    override fun setupView(view: ComposeView) {

        viewModel = Injector.INSTANCE.provideRegistrationViewModel()

        view.setContent {
            MVVMTheme {
                Scaffold {
                    Column(
                        modifier = Modifier
                            .padding(it)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom,
                    ) {

                        DefaultImageLogo(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.5f),
                            idImage = R.drawable.image_login,
                        )

                        DefaultText(text = "Registration")


                        DefaultPhoneField(
                            modifierText = Modifier
                                .padding(3.dp)
                                .weight(50f),
                            phone = viewModel.phone.value,
                            onValueChange = { newValue -> viewModel.setPhone(newValue) },
                            label = "Phone",
                            hint = "Enter your phone",
                            focusColor = R.color.default_border_focus_color,
                            unFocusColor = R.color.default_border_color,
                        )


                        DefaultPasswordField(
                            modifierText = Modifier
                                .padding(3.dp)
                                .weight(50f),
                            password = viewModel.passwordOne.value,
                            onValueChange = { newValue -> viewModel.setPasswordOne(newValue) },
                            label = "Password",
                            hint = "Enter your password",
                            focusColor = R.color.default_border_focus_color,
                            unFocusColor = R.color.default_border_color,
                        )

                        DefaultPasswordField(
                            modifierText = Modifier
                                .padding(3.dp)
                                .weight(50f),
                            password = viewModel.passwordTwo.value,
                            onValueChange = { newValue ->
                                viewModel.setPasswordTwo(newValue)
                            },
                            label = "Password",
                            hint = "Confirm password",
                            focusColor = R.color.default_border_focus_color,
                            unFocusColor = R.color.default_border_color,
                        )


                        DividerOr()

                        Row {
                            DefaultSocialAuthButton(imageId = R.drawable.google_icone)
                            DefaultSocialAuthButton(imageId = R.drawable.apple_icon)
                            DefaultSocialAuthButton(imageId = R.drawable.facebook_icon)
                        }

                        DefaultButton(
                            modifier = Modifier
                                .padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 80.dp)
                                .fillMaxWidth(),
                            text = "Create on account",
                            trailingIcon = {
                                Icon(
                                    modifier = Modifier
                                        .padding(start = 8.dp)
                                        .size(16.dp),
                                    painter = painterResource(id = R.drawable.arrow_right),
                                    contentDescription = null,
                                    tint = Color.White,
                                )
                            },
                            onClick = {
                                viewModel.saveData(
                                    viewModel.phone.value.text,
                                    viewModel.passwordOne.value.text,
                                    viewModel.passwordTwo.value.text
                                )
                            },
                        )


                        AuthFooter(
                            modifier = Modifier.padding(bottom = 8.dp),
                            text = "Already have on account?",
                            textButton = "Sing in.",
                            onClick = { viewModel.navigateToBack() },
                        )
                    }
                }
            }
        }
    }
}