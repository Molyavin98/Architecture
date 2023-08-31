package com.molyavin.mvvm.presentation.controllers.auth

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.molyavin.mvvm.R
import com.molyavin.mvvm.di.Injector
import com.molyavin.mvvm.presentation.ui.AuthFooter
import com.molyavin.mvvm.presentation.ui.DefaultButton
import com.molyavin.mvvm.presentation.ui.DefaultImageLogo
import com.molyavin.mvvm.presentation.ui.DefaultPasswordField
import com.molyavin.mvvm.presentation.ui.DefaultPhoneField
import com.molyavin.mvvm.presentation.ui.DefaultSocialAuthButton
import com.molyavin.mvvm.presentation.ui.DefaultText
import com.molyavin.mvvm.presentation.ui.DividerOr
import com.molyavin.mvvm.presentation.ui.RememberMeCheckBox
import com.molyavin.mvvm.presentation.controllers.BaseViewController
import com.molyavin.mvvm.presentation.viewmodels.auth.AuthorizationViewModel
import com.molyavin.mvvm.presentation.ui.theme.MVVMTheme

class AuthorizationController : BaseViewController() {

    override val viewModel: AuthorizationViewModel =
        Injector.INSTANCE.provideAuthorizationViewModel()

    override fun onContextAvailable(context: Context) {
        super.onContextAvailable(context)
        viewModel.attachRoot(this)
        viewModel.onBoardingScreenStatus(false)
    }

    @Composable
    override fun content() {
        Column(
            modifier = Modifier.fillMaxSize(),
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

            val email by viewModel.email.collectAsState()
            val password by viewModel.password.collectAsState()
            val statusCheckBox by viewModel.statusCheckBox.collectAsState()

            DefaultPhoneField(
                modifierText = Modifier
                    .padding(3.dp)
                    .weight(50f),
                email = email,
                onValueChange = { newPhone -> viewModel.setPhone(newPhone) },
                label = "Email",
                hint = "Enter your email",
                focusColor = R.color.default_border_focus_color,
                unFocusColor = R.color.default_border_color
            )

            DefaultPasswordField(
                modifierText = Modifier
                    .padding(3.dp)
                    .weight(50f),
                password = password,
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
                checkBoxState = statusCheckBox,
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
                onClick = { viewModel.startRegistrationController() },
            )
        }

    }
}