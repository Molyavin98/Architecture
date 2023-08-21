package com.molyavin.mvvm.presentation.screens.authorization.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bluelinelabs.conductor.Controller
import com.molyavin.mvvm.R
import com.molyavin.mvvm.domain.di.component.Injector
import com.molyavin.mvvm.presentation.DefaultSocialAuthButton
import com.molyavin.mvvm.presentation.DefaultButton
import com.molyavin.mvvm.presentation.DefaultImageLogo
import com.molyavin.mvvm.presentation.DividerOr
import com.molyavin.mvvm.presentation.DefaultPasswordField
import com.molyavin.mvvm.presentation.DefaultPhoneField
import com.molyavin.mvvm.presentation.DefaultText
import com.molyavin.mvvm.presentation.AuthFooter
import com.molyavin.mvvm.presentation.RememberMeCheckBox
import com.molyavin.mvvm.presentation.screens.authorization.presenter.AuthorizationViewModel
import com.molyavin.mvvm.presentation.ui.theme.MVVMTheme
import javax.inject.Inject

class AuthorizationController : Controller() {

    @Inject
    lateinit var viewModel: AuthorizationViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup,
        savedViewState: Bundle?
    ): View {

        Injector.INSTANCE.inject(this)

        val view = ComposeView(container.context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }

        viewModel.attachRoot(this)
        viewModel.onBoardingScreenStatus("Off")

        view.setContent {
            MVVMTheme {
                androidx.compose.material.Scaffold {
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
                                .weight(1f),
                            idImage = R.drawable.image_login,
                        )

                        DefaultText(text = "Welcome in Architecture App")

                        DefaultPhoneField(
                            modifierText = Modifier
                                .padding(3.dp)
                                .weight(50f),
                            phone = viewModel.phone.value,
                            onValueChange = { newPhone -> viewModel.setPhone(newPhone) },
                            label = "Phone",
                            hint = "Enter your phone",
                            focusColor = R.color.default_border_focus_color,
                            unFocusColor = R.color.default_border_color
                        )

                        DefaultPasswordField(
                            modifierText = Modifier
                                .padding(3.dp)
                                .weight(50f),
                            password = viewModel.password.value,
                            onValueChange = { newPassword -> viewModel.setPassword(password = newPassword) },
                            label = "Password",
                            textForgotPassword = "Forgot password?",
                            hint = "Enter your password",
                            focusColor = R.color.default_border_focus_color,
                            unFocusColor = R.color.default_border_color,
                        )

                        RememberMeCheckBox(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp),
                            checkBoxState = viewModel.statusCheckBox.value,
                            onValueChange = { newCheckBoxState ->
                                viewModel.setStatusCheckBox(
                                    status = newCheckBoxState
                                )
                            },
                            text = "Remember me"
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
                            text = "Log in",
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
                                viewModel.login()
                            },
                        )

                        AuthFooter(
                            modifier = Modifier.padding(bottom = 8.dp),
                            text = "Don`t have an account?",
                            textButton = "Sing up now.",
                            onClick = { viewModel.goToRegistrationScreen() },
                        )
                    }
                }
            }
        }

        return view
    }
}