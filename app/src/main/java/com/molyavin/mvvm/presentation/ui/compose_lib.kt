package com.molyavin.mvvm.presentation.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
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
    modifier: Modifier = Modifier.padding(10.dp),
    textAlign: TextAlign? = null,
    text: String,
    styleText: TextStyle = MaterialTheme.typography.h1,
    color: Color = colorResource(id = R.color.black),
) {
    Text(
        modifier = modifier,
        textAlign = textAlign,
        text = text,
        style = styleText,
        color = color,
    )
}

@Composable
fun DefaultImageLogo(
    modifier: Modifier = Modifier,
    idImage: Int,
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
    fieldTextStyle: TextStyle = MaterialTheme.typography.h5,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    styleText: TextStyle = MaterialTheme.typography.h5,
    fieldShape: Shape = RoundedCornerShape(16.dp),
    fieldBackgroundColor: Color = colorResource(id = R.color.default_background_text_field_color),
    trailingIcon: Int? = null,
    modifier: Modifier = Modifier,
    fieldModifier: Modifier = Modifier,
    hintStyle: TextStyle = TextStyle(),
    modifierText: Modifier = Modifier,
    trailingIconModifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 10.dp)
    ) {
        Row {
            Text(
                text = label,
                style = styleText.copy(color = Color.Gray),
                modifier = modifierText,
            )

            textForgotPassword?.let {
                Text(
                    text = it,
                    textDecoration = TextDecoration.combine(listOf(TextDecoration.Underline)),
                    style = styleText.copy(color = Color.Gray),
                    modifier = modifierText,
                    textAlign = TextAlign.End,
                )
            }
        }

        var fieldFocus by remember { mutableStateOf(false) }

        OutlinedTextField(
            modifier = fieldModifier
                .fillMaxWidth()
                .background(
                    color = fieldBackgroundColor,
                    shape = fieldShape
                )
                .onFocusChanged { fieldFocus = it.isFocused },
            value = value,
            onValueChange = onValueChange,
            keyboardOptions = keyboardOptions,
            textStyle = fieldTextStyle.copy(color = Color.Black),
            visualTransformation = visualTransformation,
            placeholder = {
                Text(
                    text = hint,
                    style = hintStyle.copy(color = Color.Gray, fontSize = 14.sp)
                )
            },
            shape = fieldShape,
            trailingIcon = {
                trailingIcon?.let {
                    Icon(
                        modifier = trailingIconModifier,
                        painter = painterResource(id = it),
                        contentDescription = null,
                        tint = if (fieldFocus) colorResource(focusColor) else Color.Gray
                    )
                }
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
    email: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    label: String,
    hint: String,
    focusColor: Int,
    unFocusColor: Int
) {
    DefaultTextField(
        value = email,
        onValueChange = onValueChange,
        label = label,
        hint = hint,
        focusColor = focusColor,
        unFocusColor = unFocusColor,
        visualTransformation = VisualTransformation.None,
        trailingIcon = R.drawable.email_icon,
        modifierText = modifierText,
    )
}

@Composable
fun DefaultPasswordField(
    modifierText: Modifier = Modifier,
    password: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    textForgotPassword: String? = null,
    label: String,
    hint: String,
    focusColor: Int,
    unFocusColor: Int
) {
    var passwordVisibility by remember { mutableStateOf(false) }

    DefaultTextField(
        modifierText = modifierText,
        value = password,
        onValueChange = onValueChange,
        label = label,
        hint = hint,
        focusColor = focusColor,
        unFocusColor = unFocusColor,
        textForgotPassword = textForgotPassword,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIconModifier = Modifier.clickable { passwordVisibility = !passwordVisibility },
        trailingIcon = if (passwordVisibility) R.drawable.baseline_visibility_24 else R.drawable.baseline_visibility_off_24
    )
}

@Composable
fun DividerOr() {
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
fun AuthFooter(
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
    checkBoxState: Boolean,
    onValueChange: (Boolean) -> Unit,
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
            checked = checkBoxState,
            onCheckedChange = onValueChange,
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
fun DefaultSocialAuthButton(
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


@Composable
fun DotsIndicator(
    modifierRow: Modifier = Modifier,
    modifier: Modifier = Modifier,
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color,
    unSelectedColor: Color,
) {

    LazyRow(
        modifier = modifierRow

    ) {

        items(totalDots) { index ->
            if (index == selectedIndex) {
                Box(
                    modifier = modifier.background(selectedColor)
                )
            } else {
                Box(
                    modifier = modifier.background(unSelectedColor)
                )
            }
            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}