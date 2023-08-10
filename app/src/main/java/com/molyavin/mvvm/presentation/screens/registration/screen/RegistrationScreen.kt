package com.molyavin.mvvm.presentation.screens.registration.screen

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.molyavin.mvvm.R

class RegistrationScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                ImageLogo()
                Text(
                    text = "Registration in App",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontStyle = FontStyle.Normal,
                        color = Color.Black
                    ),
                )
               // PhoneField()
              //  PasswordField(textForgotPassword = "", "Enter your password")
              //  PasswordField(textForgotPassword = "", "Confirm password")
                //TextOr()
             //   AccountLoginButtons()
                ButtonLoginAndRegistration()

            }
        }
    }
    @Composable
    fun ImageLogo() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                bitmap = ImageBitmap.imageResource(id = R.drawable.jetpack_compose_icon),
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
                        Text(text = "Create on account")
                    }
                }
                Row(horizontalArrangement = Arrangement.Center) {
                    Text(text = "Already have on account?", style = TextStyle(Color.Gray))
                    Text(

                        text = "Sing in.",
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