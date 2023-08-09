package com.molyavin.mvvm.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.Colors
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.molyavin.mvvm.R


@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = colorResource(id = R.color.default_button_color),
        contentColor = colorResource(id = R.color.default_button_text_color),
    ),
    trailingIcon: (@Composable () -> Unit)? = null,
    shape: Shape = RoundedCornerShape(16.dp),
    border: BorderStroke? = null,
    elevation: ButtonElevation? = null,
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    textStyle: TextStyle = MaterialTheme.typography.subtitle1,
) {
    Button(
        modifier = modifier.defaultMinSize(minHeight = 40.dp, minWidth = 100.dp),
        onClick = onClick,
        enabled = enabled,
        border = border,
        elevation = elevation,
        shape = shape,
        colors = colors,
        contentPadding = contentPadding,
    ) {
        Text(
            text = text,
            style = textStyle,
        )

        trailingIcon?.invoke()
    }

}


@Preview
@Composable
fun PhoneField() {

    val phone = remember {
        mutableStateOf(TextFieldValue())
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp, top = 50.dp)
    ) {
        Text(
            text = "Phone number",
            style = TextStyle(color = Color.Gray, fontSize = 12.sp),
            modifier = Modifier.padding(3.dp)
        )

        var fieldFocus by remember { mutableStateOf(false) }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { fieldFocus = it.isFocused },
            placeholder = {
                Text(
                    text = "Enter your phone number",
                    style = TextStyle(color = Color.Gray, fontSize = 14.sp)
                )
            },
            value = phone.value,
            onValueChange = { phone.value = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            shape = RoundedCornerShape(10.dp),
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_phone_enabled_24),
                    contentDescription = null,
                    tint = if (fieldFocus) Color(0xFF3ddc84) else Color.Gray
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF3ddc84),
                unfocusedBorderColor = Color.Gray,
                cursorColor = Color(0xFF3ddc84),
            ),
        )
    }
}

@Composable
fun PasswordField(textForgotPassword: String, hint: String) {

    val password = remember { mutableStateOf(TextFieldValue()) }
    var passwordVisibility: Boolean by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp, top = 10.dp)
    ) {
        Row {
            Text(
                text = "Password",
                style = TextStyle(color = Color.Gray, fontSize = 12.sp),
                modifier = Modifier
                    .padding(3.dp)
                    .weight(50f)
            )
            Text(
                text = textForgotPassword,
                style = TextStyle(color = Color.Gray, fontSize = 12.sp),
                modifier = Modifier
                    .padding(3.dp)
                    .weight(50f),
                textAlign = TextAlign.End
            )
        }

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password.value,
            onValueChange = { password.value = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            placeholder = {
                Text(
                    text = hint,
                    style = TextStyle(color = Color.Gray, fontSize = 14.sp)
                )
            },
            shape = RoundedCornerShape(10.dp),
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_visibility_24),
                    contentDescription = null,
                    modifier = Modifier.clickable { passwordVisibility = !passwordVisibility }
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF3ddc84),
                unfocusedBorderColor = Color.Gray,
                cursorColor = Color(0xFF3ddc84),
            ),
        )
    }
}

@Composable
fun TextOr() {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 15.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .weight(40f)
                .background(Color.LightGray)
        )
        Text(
            text = "or",
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp),
            style = TextStyle(Color.Gray)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .weight(40f)
                .background(Color.LightGray)
        )
    }
}

@Composable
fun AccountLoginButtons() {

    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        IconButton(onClick = {}) {
            Image(
                painter = painterResource(id = R.drawable.facebook_icon),
                contentDescription = null,
            )
        }

        IconButton(onClick = {}) {
            Image(
                painter = painterResource(id = R.drawable.apple_icon),
                contentDescription = null
            )

        }
    }
}