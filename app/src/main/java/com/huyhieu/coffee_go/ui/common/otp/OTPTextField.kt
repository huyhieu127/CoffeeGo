package com.huyhieu.coffee_go.ui.common.otp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.huyhieu.coffee_go.ui.theme.utils.type.FontStyle
import com.huyhieu.coffee_go.ui.theme.utils.type.size
import kotlinx.coroutines.android.awaitFrame

@Preview
@Composable
fun OTPTextField(
    modifier: Modifier = Modifier,
    length: Int = 4,
    isPIN: Boolean = false,
    onValueChange: (otp: String) -> Unit = {},
) {
    val focusRequester = remember { FocusRequester() }
    val keyboard = LocalSoftwareKeyboardController.current

    var otpCode by remember {
        mutableStateOf("")
    }
    var indexActive by remember {
        mutableIntStateOf(0)
    }

    LaunchedEffect(focusRequester) {
        awaitFrame()
        focusRequester.requestFocus()
        keyboard?.show()
    }
    BasicTextField(
        modifier = modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        value = otpCode,
        onValueChange = { newValue ->
            if (newValue.length <= length) {
                otpCode = newValue
                indexActive = newValue.length
                onValueChange.invoke(otpCode)
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            repeat(length) { index ->
                val number = when {
                    index >= otpCode.length -> ""
                    else -> if (isPIN) {
                        "●"
                    } else {
                        otpCode[index].toString()
                    }
                }
                Box(
                    modifier = Modifier
                        .width(60.dp)
                        .height(52.dp)
                        .boxOtp(isActive = indexActive == index),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = number,
                        style = FontStyle.SemiBold.size(28.sp),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = if (isPIN) 2.dp else 0.dp)
                    )

                }
            }
        }
    }
}