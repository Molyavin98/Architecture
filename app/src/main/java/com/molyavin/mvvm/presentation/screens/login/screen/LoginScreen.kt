package com.molyavin.mvvm.presentation.screens.login.screen

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.molyavin.mvvm.R
import com.molyavin.mvvm.presentation.AccountLoginButtons
import com.molyavin.mvvm.presentation.PasswordField
import com.molyavin.mvvm.presentation.PhoneField
import com.molyavin.mvvm.presentation.TextOr
import com.molyavin.mvvm.presentation.screens.registration.screen.RegistrationScreen

class LoginScreen : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ImageLogo()
                Text(
                    text = "Welcome to Architecture App",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontStyle = FontStyle.Normal,
                        color = Color.Black
                    ),
                )
                PhoneField()
                PasswordField("Forgot password?", "Enter your password")
                RememberMeCheckBox()
                TextOr()
                AccountLoginButtons()
                ButtonLoginAndRegistration()

            }
        }
    }

    @Composable
    fun RememberMeCheckBox() {

        val checkState = remember {
            mutableStateOf(false)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checkState.value,
                onCheckedChange = { checkState.value = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color(0xFF3ddc84),
                    uncheckedColor = Color.Gray
                )
            )
            Text(text = "Remember me", fontSize = 12.sp, textAlign = TextAlign.Center)
        }
    }

    @Composable
    fun ImageLogo() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                bitmap = ImageBitmap.imageResource(id = R.drawable.image_login),
                contentDescription = "Login image",
                modifier = Modifier.fillMaxSize()
            )
        }
    }
    @Composable
    fun ButtonLoginAndRegistration() {

       val intent = Intent(this, RegistrationScreen::class.java)

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {


            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(start = 15.dp, end = 15.dp)
                ) {

                    Button(
                        onClick = { },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(Color(0xFF3ddc84))
                    ) {
                        Text(text = "Log in")
                    }
                }
                Row(horizontalArrangement = Arrangement.Center) {
                    Text(text = "Don`t have an account?", style = TextStyle(Color.Gray))
                    Text(

                        text = "Sing up now.",
                        style = TextStyle(Color.Blue),
                        modifier = Modifier
                            .padding(start = 2.dp)
                            .clickable { startActivity(intent) }
                    )
                }
            }
        }
    }

}