package com.molyavin.mvvm.presentation.controllers.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.molyavin.mvvm.R
import com.molyavin.mvvm.di.Injector
import com.molyavin.mvvm.presentation.ui.DefaultButton
import com.molyavin.mvvm.presentation.ui.DefaultImageLogo
import com.molyavin.mvvm.presentation.ui.DefaultText
import com.molyavin.mvvm.presentation.ui.DotsIndicator
import com.molyavin.mvvm.presentation.controllers.BaseViewController
import com.molyavin.mvvm.presentation.ui.theme.MVVMTheme
import com.molyavin.mvvm.presentation.viewmodels.onboarding.OnBoardingViewModel

class OnBoardingController : BaseViewController() {

    override val viewModel: OnBoardingViewModel = Injector.INSTANCE.provideOnBoardingViewModel()

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun content() {
        Scaffold {
            val slides by viewModel.slides.collectAsState()
            val currentSliderPosition by viewModel.currentSliderPosition.collectAsState()
            val slideState = rememberPagerState()

            LaunchedEffect(
                key1 = currentSliderPosition,
                block = { slideState.scrollToPage(currentSliderPosition) }
            )

            HorizontalPager(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                pageCount = slides.size,
                state = slideState,
            ) { position ->
                val item = slides[position]

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom,
                ) {

                    DefaultImageLogo(
                        idImage = item.idImage,
                        modifier = Modifier.weight(1f)
                    )

                    DefaultText(
                        modifier = Modifier.padding(32.dp),
                        text = item.title,
                        textAlign = TextAlign.Center
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                            .background(colorResource(id = R.color.default_button_color)),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom
                    ) {

                        DotsIndicator(
                            modifierRow = Modifier
                                .padding(top = 32.dp)
                                .weight(1f),
                            modifier = Modifier
                                .width(24.dp)
                                .height(8.dp)
                                .clip(CircleShape),
                            totalDots = 4,
                            selectedIndex = position,
                            selectedColor = Color.White,
                            unSelectedColor = Color.Gray
                        )


                        DefaultText(
                            modifier = Modifier
                                .padding(
                                    start = 32.dp,
                                    end = 32.dp,
                                    bottom = 64.dp
                                ),
                            textAlign = TextAlign.Center,
                            text = slides[position].description,
                            styleText = MaterialTheme.typography.h5,
                            color = Color.Gray,
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 32.dp, end = 32.dp, bottom = 24.dp),
                        ) {
                            DefaultButton(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(end = 6.dp)
                                    .weight(1f),
                                text = "Skip",
                                colors =
                                ButtonDefaults.buttonColors(
                                    backgroundColor = colorResource(id = R.color.button_color),
                                    contentColor = colorResource(id = R.color.white),
                                ),
                                onClick = viewModel::startAuthController
                            )


                            DefaultButton(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 6.dp)
                                    .weight(1f),
                                text = "Next",
                                colors =
                                ButtonDefaults.buttonColors(
                                    backgroundColor = colorResource(id = R.color.white),
                                    contentColor = colorResource(id = R.color.black),
                                ),
                                trailingIcon = {
                                    Icon(
                                        modifier = Modifier
                                            .padding(start = 8.dp)
                                            .size(16.dp),
                                        painter = painterResource(id = R.drawable.arrow_right),
                                        contentDescription = null,
                                        tint = Color.Black,
                                    )
                                },
                                onClick = viewModel::nextSlide
                            )
                        }
                    }
                }
            }
        }

    }
}