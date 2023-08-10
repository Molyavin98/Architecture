package com.molyavin.mvvm.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
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

@Composable
fun DefaultText(
    text: String,
    styleText: TextStyle = MaterialTheme.typography.h1
) {
    Text(
        modifier = Modifier.padding(10.dp),
        text = text,
        style = styleText,
        color = colorResource(id = R.color.black)
    )
}

@Composable
fun DefaultImageLogo(
    modifier: Modifier = Modifier,
    idImage: Int
) {
    Image(
        modifier = modifier,
        bitmap = ImageBitmap.imageResource(id = idImage),
        contentDescription = null,

        )
}

@Composable
fun DefaultTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    label: String,
    hint: String,
    focusColor: Int,
    unFocusColor: Int,
    textForgotPassword: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    styleText: TextStyle = MaterialTheme.typography.h5,
    trailingIcon: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    modifierText: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 10.dp)
    ) {

        Row {
            Text(
                text = label,
                style = styleText,
                color = Color.Gray,
                modifier = modifierText,
            )

            if (textForgotPassword != null) {
                Text(
                    text = textForgotPassword,
                    textDecoration = TextDecoration.combine(listOf(TextDecoration.Underline)),
                    style = styleText,
                    color = Color.Gray,
                    modifier = modifierText,
                    textAlign = TextAlign.End,
                )

            }
        }

        var fieldFocus by remember { mutableStateOf(false) }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    colorResource(id = R.color.default_background_text_field_color),
                    shape = RoundedCornerShape(16.dp)
                )
                .onFocusChanged { fieldFocus = it.isFocused },
            value = value,
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            textStyle = TextStyle(color = Color.Black),
            visualTransformation = visualTransformation,
            placeholder = {
                Text(
                    text = hint,
                    style = TextStyle(color = Color.Gray, fontSize = 14.sp)
                )
            },
            shape = RoundedCornerShape(16.dp),
            trailingIcon = {
                trailingIcon?.invoke()
            },

            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorResource(focusColor),
                unfocusedBorderColor = colorResource(unFocusColor),
                focusedLabelColor = colorResource(focusColor),
                unfocusedLabelColor = Color.Gray,
                cursorColor = colorResource(focusColor),
            ),
        )
    }
}

@Composable
fun DefaultPhoneField(
    modifierText: Modifier = Modifier,
    phone: MutableState<TextFieldValue>,
    label: String,
    hint: String,
    focusColor: Int,
    unFocusColor: Int
) {
    DefaultTextField(
        modifierText = modifierText,
        value = phone.value,
        onValueChange = { phone.value = it },
        label = label,
        hint = hint,
        focusColor = focusColor,
        unFocusColor = unFocusColor,
        keyboardType = KeyboardType.Phone,
        visualTransformation = VisualTransformation.None,

        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.baseline_phone_enabled_24),
                contentDescription = null,
                tint = if (phone.value.text.isNotEmpty()) colorResource(id = R.color.default_border_color) else Color.Gray
            )
        }
    )
}

@Composable
fun DefaultPasswordField(
    modifierText: Modifier = Modifier,
    password: MutableState<TextFieldValue>,
    textForgotPassword: String? = null,
    label: String,
    hint: String,
    focusColor: Int,
    unFocusColor: Int
) {
    var passwordVisibility by remember { mutableStateOf(false) }

    DefaultTextField(
        modifierText = modifierText,
        value = password.value,
        onValueChange = { password.value = it },
        label = label,
        hint = hint,
        focusColor = focusColor,
        unFocusColor = unFocusColor,
        textForgotPassword = textForgotPassword,
        keyboardType = KeyboardType.Password,
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            Icon(
                painter = if (passwordVisibility) painterResource(id = R.drawable.baseline_visibility_24) else painterResource(
                    id = R.drawable.baseline_visibility_off_24
                ),
                contentDescription = null,
                tint = if (password.value.text.isNotEmpty()) colorResource(id = R.color.default_border_color) else Color.Gray,
                modifier = Modifier.clickable { passwordVisibility = !passwordVisibility }
            )
        }
    )
}

@Composable
fun DefaultLineAndTextOr() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 8.dp, bottom = 12.dp)
    ) {
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
fun DefaultAuthFooter(
    modifier: Modifier,
    text: String,
    textButton: String,
    onClick: () -> Unit,
    styleText: TextStyle = MaterialTheme.typography.h5
) {

    Row(modifier = modifier) {
        Text(
            text = text,
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
            color = Color.Black,
            style = styleText,
        )
        Text(
            text = textButton,
            style = styleText,
            color = Color.Blue,
            modifier = Modifier
                .padding(top = 8.dp, bottom = 8.dp)
                .clickable { onClick.invoke() }
        )
    }

}

@Composable
fun RememberMeCheckBox(
    modifier: Modifier = Modifier,
    checkState: MutableState<Boolean>,
    styleText: TextStyle = MaterialTheme.typography.h5,
    checkedColor: Color = colorResource(id = R.color.default_checkbox_color),
    checkMarkColor: Color = colorResource(id = R.color.white),
    text: String,
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = checkState.value,
            onCheckedChange = { checkState.value = it },
            colors = CheckboxDefaults.colors(
                checkedColor = checkedColor,
                checkmarkColor = checkMarkColor,
                uncheckedColor = Color.Gray
            )
        )
        Text(
            text = text,
            color = Color.Gray,
            style = styleText,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun DefaultAccountLoginButton(
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = colorResource(id = R.color.default_button_account_color)
    ),
    imageId: Int,
    shape: Shape = RoundedCornerShape(16.dp),
    border: BorderStroke = BorderStroke(1.dp, colorResource(id = R.color.default_border_color))
) {
    Button(
        modifier = modifier
            .padding(5.dp)
            .size(50.dp),
        colors = colors,
        shape = shape,
        border = border,
        onClick = {},
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = null,
        )
    }
}