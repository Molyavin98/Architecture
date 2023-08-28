package com.molyavin.mvvm.presentation.controllers

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.Icon
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
import com.molyavin.mvvm.domain.di.component.Injector
import com.molyavin.mvvm.presentation.DefaultButton
import com.molyavin.mvvm.presentation.DefaultImageLogo
import com.molyavin.mvvm.presentation.DefaultText
import com.molyavin.mvvm.presentation.DotsIndicator
import com.molyavin.mvvm.presentation.ui.theme.MVVMTheme
import com.molyavin.mvvm.presentation.viewmodels.OnBoardingViewModel

class OnBoardingController : BaseViewController() {

    override lateinit var viewModel: OnBoardingViewModel

    @OptIn(ExperimentalFoundationApi::class)
    override fun setupView(view: ComposeView) {

        viewModel = Injector.INSTANCE.provideOnBoardingViewModel()

        var count = 0


        view.setContent {
            MVVMTheme {
                Scaffold {
                    Column {
                        Column(
                            modifier = Modifier
                                .padding(it)
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Bottom
                        ) {

                            val slideCount = viewModel.slideCount.value
                            val slideState = rememberPagerState()

                            HorizontalPager(
                                modifier = Modifier.padding(top = 24.dp),
                                pageCount = slideCount,
                                contentPadding = PaddingValues(horizontal = 64.dp),
                                pageSpacing = 24.dp,
                                state = slideState
                            ) {}


                            DefaultImageLogo(
                                idImage = viewModel.getSlides()[slideCount].idImage,
                                modifier = Modifier.weight(1f)
                            )

                            DefaultText(
                                modifier = Modifier.padding(32.dp),
                                text = viewModel.getSlides()[slideCount].title,
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
                                    selectedIndex = slideCount,
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
                                    text = viewModel.getSlides()[slideCount].description,
                                    styleText = MaterialTheme.typography.h5.copy(),
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
                                        onClick = {
                                            viewModel.startScreen(AuthorizationController())
                                        })


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
                                        onClick = {
                                            count++
                                            viewModel.nextSlide(count)
                                            if (slideCount == 3) {
                                                this@OnBoardingController.viewModel.startScreen(
                                                    AuthorizationController()
                                                )
                                            }
                                        })
                                }

                            }
                        }
                    }

                }
            }
        }

    }
}