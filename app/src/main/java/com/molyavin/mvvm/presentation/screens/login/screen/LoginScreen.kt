package com.molyavin.mvvm.presentation.screens.login.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.molyavin.mvvm.R
import com.molyavin.mvvm.presentation.DefaultButton
import com.molyavin.mvvm.presentation.ui.theme.MVVMTheme

class LoginScreen : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMTheme {
                Scaffold {
                    Column(
                        modifier = Modifier
                            .padding(it)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom,
                    ) {
                      //  ImageLogo()
                        Text(
                            text = "Welcome to Architecture App",
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontStyle = FontStyle.Normal,
                                color = Color.Black,
                            ),
                        )

                        val password = remember {
                            mutableStateOf(TextFieldValue())
                        }

                     /*   DefaultPasswordField(
                            password = password,
                            textForgotPassword = "Forgot password?",
                            hint = "Enter your password"
                        )*/

                    }


                }
            }

        }
    }
}




/*

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

*/
