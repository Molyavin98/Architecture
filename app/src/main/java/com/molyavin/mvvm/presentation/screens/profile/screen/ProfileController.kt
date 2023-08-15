package com.molyavin.mvvm.presentation.screens.profile.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.bluelinelabs.conductor.Controller
import com.molyavin.mvvm.R
import com.molyavin.mvvm.domain.di.component.Injector
import com.molyavin.mvvm.presentation.DefaultButton
import com.molyavin.mvvm.presentation.DefaultImageLogo
import com.molyavin.mvvm.presentation.DefaultText
import com.molyavin.mvvm.presentation.screens.profile.viewmodel.ProfileViewModel
import com.molyavin.mvvm.presentation.ui.theme.MVVMTheme
import javax.inject.Inject

class ProfileController : Controller() {

    @Inject
    lateinit var viewModel: ProfileViewModel

    @SuppressLint("SetTextI18n", "NewApi")
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

                        DefaultText(
                            modifier = Modifier
                                .padding(top = 20.dp),
                            text = "Full name: ${viewModel.userInfoLiveData.value?.fullName}"
                        )


                        DefaultText(
                            modifier = Modifier.padding(0.dp),
                            text = "Phone: ${viewModel.userInfoLiveData.value?.phone}"
                        )


                        DefaultText(
                            modifier = Modifier.padding(0.dp),
                            text = "Password: ${viewModel.userInfoLiveData.value?.password}"
                        )

                        DefaultButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(16.dp)
                                .wrapContentHeight(align = Alignment.Bottom),
                            text = "Exit from account",
                            onClick = { viewModel.exitFromProfile() })

                    }
                }
            }
        }

        viewModel.onCreate()

        return view
    }
}