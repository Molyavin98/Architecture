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
import com.molyavin.mvvm.presentation.controllers.BaseViewController
import com.molyavin.mvvm.presentation.viewmodels.auth.RegistrationViewModel

class RegistrationController : BaseViewController() {

    override val viewModel: RegistrationViewModel = Injector.INSTANCE.provideRegistrationViewModel()

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
                    .weight(0.5f),
                idImage = R.drawable.image_login,
            )

            DefaultText(text = "Registration")

            val email by viewModel.email.collectAsState()
            val password by viewModel.password.collectAsState()
            val passwordConfirm by viewModel.passwordConfirm.collectAsState()


            DefaultPhoneField(
                modifierText = Modifier
                    .padding(3.dp)
                    .weight(50f),
                email = email,
                onValueChange = { newValue -> viewModel.setPhone(newValue) },
                label = "Email",
                hint = "Enter your email",
                focusColor = R.color.default_border_focus_color,
                unFocusColor = R.color.default_border_color,
            )


            DefaultPasswordField(
                modifierText = Modifier
                    .padding(3.dp)
                    .weight(50f),
                password = password,
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
                password = passwordConfirm,
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
                    viewModel.registration()
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