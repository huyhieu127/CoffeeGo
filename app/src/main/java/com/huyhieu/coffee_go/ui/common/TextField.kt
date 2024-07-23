package com.huyhieu.coffee_go.ui.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.huyhieu.coffee_go.R
import com.huyhieu.coffee_go.ui.common.button.clickableNoneRipple
import com.huyhieu.coffee_go.ui.theme.Line
import com.huyhieu.coffee_go.ui.theme.Primary
import com.huyhieu.coffee_go.ui.theme.PrimaryLight
import com.huyhieu.coffee_go.ui.theme.TextBlack
import com.huyhieu.coffee_go.ui.theme.TextPlaceholder
import com.huyhieu.coffee_go.ui.theme.utils.type.FontStyle
import com.huyhieu.coffee_go.ui.theme.utils.type.size

@Preview
@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    @DrawableRes iconPrefix: Int = 0,
    prefix: @Composable ((Color) -> Unit)? = null,
    @DrawableRes iconSuffix: Int = 0,
    value: String = "",
    placeholder: String = "Email",
    isPassword: Boolean = false,
    isSingleLine: Boolean = true,
    isEnable: Boolean = true,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    interactionSource: MutableInteractionSource = remember {
        MutableInteractionSource()
    },
    onPrefixClicked: () -> Unit = {},
    onSuffixClicked: () -> Unit = {},
    onValueChange: (String) -> Unit = {},
) {

    var isFocused by remember {
        mutableStateOf(false)
    }
    var color by remember {
        mutableStateOf(TextPlaceholder)
    }
    var eye by remember {
        mutableIntStateOf(R.drawable.ic_eye_close)
    }
    var isShowPassword by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(isFocused) {
        color = when {
            isFocused -> Primary
            value.isNotEmpty() -> TextBlack
            else -> TextPlaceholder
        }
    }
    LaunchedEffect(isShowPassword) {
        eye = if (isShowPassword) {
            R.drawable.ic_eye
        } else {
            R.drawable.ic_eye_close
        }
    }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        maxLines = 1,
        enabled = isEnable,
        isError = isError,
        singleLine = isSingleLine,
        interactionSource = interactionSource,
        keyboardOptions = keyboardOptions,
        placeholder = if (placeholder.isNotEmpty()) {
            {
                Text(
                    text = placeholder,
                    color = TextPlaceholder,
                    textAlign = TextAlign.Center,
                    style = FontStyle.Medium.size(16.sp),
                    maxLines = 1,
                )
            }
        } else null,
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = Primary,
            unfocusedContainerColor = Line,
            unfocusedBorderColor = Line,
            focusedContainerColor = PrimaryLight,
            focusedBorderColor = Primary,
        ), prefix = {
            if (prefix != null) {
                prefix(color)
            } else if (iconPrefix != 0) {
                Row {
                    Icon(
                        painter = painterResource(id = iconPrefix),
                        contentDescription = null,
                        tint = color,
                        modifier = Modifier
                            .size(18.dp)
                            .clickableNoneRipple {
                                onPrefixClicked()
                            }
                    )
                    SpacerHorizontal(8.dp)
                }
            }
        },
        suffix = {
            if (iconSuffix != 0 || isPassword) {
                val icon = if (isPassword) eye else iconSuffix
                Row {
                    SpacerHorizontal(8.dp)
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                        tint = color,
                        modifier = Modifier
                            .size(18.dp)
                            .clickableNoneRipple {
                                if (isPassword) {
                                    isShowPassword = !isShowPassword
                                } else {
                                    onSuffixClicked()
                                }
                            })
                }

            }
        },
        shape = RoundedCornerShape(14.dp),
        visualTransformation = if (isPassword) {
            if (isShowPassword) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            }
        } else VisualTransformation.None,
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .onFocusChanged {
                isFocused = it.isFocused
            })
}