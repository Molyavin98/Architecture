package com.molyavin.mvvm.presentation.ui

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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
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
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.molyavin.mvvm.R
import com.molyavin.mvvm.domain.models.WordVM

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
fun HorizontalLine() {
    Box(
        modifier = Modifier
            .padding(start = 16.dp, top = 30.dp, end = 16.dp)
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.LightGray)
    )
}

@Composable
fun ListElementItemButton(
    iconImage: ImageVector,
    colorTint: Color,
    text: String,
    textColor: Color,
    textStyle: TextStyle,
    onClick: () -> Unit,
    buttonImage: ImageVector,
    buttonColorTint: Color
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true, color = Color.LightGray),
                onClick = onClick
            )
            .padding(start = 20.dp, top = 10.dp, bottom = 10.dp, end = 20.dp),
    ) {
        Icon(
            modifier = Modifier
                .padding(end = 15.dp)
                .size(40.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(color = colorResource(id = R.color.background_button_profile))
                .padding(10.dp),
            imageVector = iconImage,
            tint = colorTint,
            contentDescription = null
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .align(Alignment.CenterVertically),
            text = text,
            color = textColor,
            style = textStyle
        )

        Icon(
            modifier = Modifier
                .size(16.dp)
                .align(Alignment.CenterVertically),
            imageVector = buttonImage,
            tint = buttonColorTint,
            contentDescription = null
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultCenterAlignedTopAppBar(
    modifier: Modifier,
    textTitle: String,
    titleContentColor: Color,
    textStyleTitle: TextStyle,
    navigationOnClick: () -> Unit,
    navigationIcon: ImageVector,
    navigationIconTint: Color,
    actionOnClick: () -> Unit,
    actionIcon: ImageVector? = null,
    actionIconTint: Color? = null,
    containerColor: Color
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = textTitle,
                color = titleContentColor,
                style = textStyleTitle,
            )
        },
        navigationIcon = {
            IconButton(onClick = navigationOnClick) {
                Icon(
                    imageVector = navigationIcon,
                    tint = navigationIconTint,
                    contentDescription = null,
                )
            }
        },
        actions = {
            IconButton(onClick = actionOnClick) {
                if (actionIcon != null && actionIconTint != null) {
                    Icon(
                        imageVector = actionIcon,
                        tint = actionIconTint,
                        contentDescription = null,
                    )
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = containerColor,
        )
    )
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
        painter = painterResource(id = idImage),
        contentDescription = null,
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DefaultGlideImage(
    modifier: Modifier = Modifier,
    contentScale: ContentScale,
    urlImage: String,
) {
    GlideImage(
        modifier = modifier,
        model = urlImage,
        contentScale = contentScale,
        contentDescription = null,
        loading = placeholder {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = colorResource(id = R.color.default_button_color)
                )
            }
        }
    ) {

        it.error("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png")

    }

}

@Composable
private fun DefaultTextField(
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
fun DefaultEmailField(
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
fun DefaultTextField(
    modifierText: Modifier = Modifier,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    label: String = "",
    hint: String,
    focusColor: Int,
    unFocusColor: Int
) {
    DefaultTextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        hint = hint,
        focusColor = focusColor,
        unFocusColor = unFocusColor,
        visualTransformation = VisualTransformation.None,
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
fun LazyList(
    modifier: Modifier,
    words: List<WordVM>,
    modifierItem: Modifier,
    containerColor: Int,
    textStyleEngWord: TextStyle = MaterialTheme.typography.h3,
    textStyleUAWord: TextStyle = MaterialTheme.typography.h4,
    buttonModifier: Modifier,
    modifierText: Modifier,
    btnDeleteText: String,
    btnDeleteClick: (Int) -> Unit,
    btnEditText: String,
    btnEditClick: (Int) -> Unit

) {
    val selectedItem = remember { mutableStateOf<WordVM?>(null) }

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        itemsIndexed(words) { index, word ->

            val isItemSelected = word == selectedItem.value

            Card(
                modifier = modifierItem
                    .clickable {
                        if (isItemSelected) {
                            selectedItem.value = null
                        } else {
                            selectedItem.value = word
                        }
                    },
                colors = CardDefaults.cardColors(containerColor = colorResource(id = containerColor)),
            ) {
                Row() {

                    Column(modifier = Modifier.weight(1f)) {

                        Text(
                            modifier = modifierText,
                            text = "\uD83C\uDDEC\uD83C\uDDE7 ${word.eng}",
                            style = textStyleEngWord
                        )
                        Text(
                            modifier = modifier,
                            text = "\uD83C\uDDFA\uD83C\uDDE6 ${word.ua}",
                            style = textStyleUAWord
                        )

                    }

                    val isFavorite = remember { mutableStateOf(false) }

                    IconButton(modifier = Modifier.align(Alignment.CenterVertically),
                        onClick = {
                            isFavorite.value = !isFavorite.value
                        }
                    ) {
                        Icon(
                            imageVector = if (isFavorite.value) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = null
                        )
                    }
                }

                if (isItemSelected) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {

                        DefaultButton(
                            modifier = buttonModifier,
                            border = BorderStroke(1.dp, Color.White),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Transparent,
                                contentColor = colorResource(id = R.color.default_button_text_color)
                            ),
                            contentPadding = PaddingValues(0.dp),
                            text = btnDeleteText,
                            onClick = { btnDeleteClick(index) })

                        DefaultButton(
                            modifier = buttonModifier,
                            border = BorderStroke(1.dp, Color.White),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Transparent,
                                contentColor = colorResource(id = R.color.default_button_text_color)
                            ),
                            contentPadding = PaddingValues(0.dp),
                            text = btnEditText,
                            onClick = { btnEditClick(index) })
                    }
                }
            }
        }
    }
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
fun HalfColoredText(
    modifier: Modifier,
    firstHalfText: String,
    fisrtHalfColor: Color,
    firstHalfTextStyle: TextStyle,
    secondHalfText: String,
    secondHalfColor: Color,
    secondHalfStyle: TextStyle,
) {
    Row(modifier = modifier) {
        Text(
            text = firstHalfText,
            color = fisrtHalfColor,
            style = firstHalfTextStyle
        )
        Text(
            text = secondHalfText,
            color = secondHalfColor,
            style = secondHalfStyle
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




