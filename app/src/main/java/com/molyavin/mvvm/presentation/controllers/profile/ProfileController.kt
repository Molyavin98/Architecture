package com.molyavin.mvvm.presentation.controllers.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.molyavin.mvvm.R
import com.molyavin.mvvm.di.Injector
import com.molyavin.mvvm.presentation.controllers.BaseViewController
import com.molyavin.mvvm.presentation.ui.DefaultButton
import com.molyavin.mvvm.presentation.ui.DefaultCenterAlignedTopAppBar
import com.molyavin.mvvm.presentation.ui.DefaultGlideImage
import com.molyavin.mvvm.presentation.ui.DefaultText
import com.molyavin.mvvm.presentation.ui.HalfColoredText
import com.molyavin.mvvm.presentation.ui.HorizontalLine
import com.molyavin.mvvm.presentation.ui.ListElementItemButton
import com.molyavin.mvvm.presentation.viewmodels.profile.ProfileViewModel

class ProfileController : BaseViewController() {

    override val viewModel: ProfileViewModel = Injector.INSTANCE.provideProfileViewModel()


    @Composable
    override fun content() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {


            DefaultCenterAlignedTopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                textTitle = "Profile",
                titleContentColor = Color.Black,
                textStyleTitle = MaterialTheme.typography.h2,
                navigationOnClick = { viewModel.navigateToBack() },
                navigationIcon = Icons.Default.KeyboardArrowLeft,
                navigationIconTint = Color.Black,
                actionOnClick = { },
                actionIcon = Icons.Default.Menu,
                actionIconTint = Color.Black,
                containerColor = Color.Transparent
            )

            val userInfo by viewModel.userInfo.collectAsState()

            DefaultGlideImage(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .size(120.dp)
                    .clip(CircleShape)
                    .border(
                        border = BorderStroke(
                            width = 2.dp,
                            color = colorResource(id = R.color.border_color),
                        ),
                        shape = CircleShape,
                    )
                    .padding(2.dp)
                    .border(
                        border = BorderStroke(
                            width = 2.dp,
                            color = Color.White,
                        ),
                        shape = CircleShape,
                    ),
                contentScale = ContentScale.Crop,
                urlImage = userInfo.urlImage
            )

            DefaultText(
                modifier = Modifier
                    .padding(top = 20.dp),
                text = userInfo.email,
                styleText = MaterialTheme.typography.h2,
            )

            DefaultText(
                modifier = Modifier
                    .padding(top = 5.dp),
                text = "@${userInfo.id}",
                styleText = MaterialTheme.typography.h5,
                color = colorResource(id = R.color.gray)
            )

            DefaultButton(
                modifier = Modifier
                    .padding(20.dp)
                    .height(40.dp)
                    .width(150.dp),
                shape = RoundedCornerShape(25.dp),
                textStyle = MaterialTheme.typography.h4,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.border_color),
                    contentColor = Color.White
                ),
                text = "Edit Profile",
                contentPadding = PaddingValues(0.dp),
                onClick = {})

            HorizontalLine()

            //Settings
            ListElementItemButton(
                iconImage = Icons.Default.Settings,
                colorTint = colorResource(id = R.color.image_color_in_menu),
                text = "Settings",
                textColor = Color.Black,
                textStyle = MaterialTheme.typography.h4,
                onClick = { viewModel.startSettings() },
                buttonImage = Icons.Default.KeyboardArrowRight,
                buttonColorTint = Color.Black
            )

            //Billing Details
            ListElementItemButton(
                iconImage = Icons.Default.Warning,
                colorTint = colorResource(id = R.color.image_color_in_menu),
                text = "Billing Details",
                textColor = Color.Black,
                textStyle = MaterialTheme.typography.h4,
                onClick = { },
                buttonImage = Icons.Default.KeyboardArrowRight,
                buttonColorTint = Color.Black
            )

            //User Management
            ListElementItemButton(
                iconImage = Icons.Default.AccountCircle,
                colorTint = colorResource(id = R.color.image_color_in_menu),
                text = "User Management",
                textColor = Color.Black,
                textStyle = MaterialTheme.typography.h4,
                onClick = { },
                buttonImage = Icons.Default.KeyboardArrowRight,
                buttonColorTint = Color.Black
            )

            HorizontalLine()

            //Information
            ListElementItemButton(
                iconImage = Icons.Default.Info,
                colorTint = colorResource(id = R.color.image_color_in_menu),
                text = "Information",
                textColor = Color.Black,
                textStyle = MaterialTheme.typography.h4,
                onClick = { },
                buttonImage = Icons.Default.KeyboardArrowRight,
                buttonColorTint = Color.Black
            )

            //Log out
            ListElementItemButton(
                iconImage = Icons.Default.ExitToApp,
                colorTint = colorResource(id = R.color.image_color_in_menu),
                text = "Log out",
                textColor = Color.Black,
                textStyle = MaterialTheme.typography.h4,
                onClick = { viewModel.logOut() },
                buttonImage = Icons.Default.KeyboardArrowRight,
                buttonColorTint = Color.Black
            )

            userInfo.creationTime?.let {
                HalfColoredText(
                    modifier = Modifier
                        .padding(
                            start = 16.dp,
                            top = 36.dp,
                            bottom = 16.dp
                        ),
                    firstHalfText = "Joined ",
                    fisrtHalfColor = Color.Gray,
                    firstHalfTextStyle = MaterialTheme.typography.h5,
                    secondHalfText = it,
                    secondHalfColor = Color.Black,
                    secondHalfStyle = MaterialTheme.typography.h5
                )
            }
        }
    }
}