package com.huyhieu.listentogether.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.huyhieu.coffee_go.R
import com.huyhieu.coffee_go.ui.common.button.ButtonIcon
import com.huyhieu.coffee_go.ui.common.SpacerHorizontal
import com.huyhieu.coffee_go.ui.common.SpacerVertical
import com.huyhieu.coffee_go.ui.theme.Line
import com.huyhieu.coffee_go.ui.theme.TextBlack
import com.huyhieu.coffee_go.ui.theme.TextNormal
import com.huyhieu.coffee_go.ui.theme.utils.brush.BrushStyle
import com.huyhieu.coffee_go.ui.theme.utils.type.FontStyle
import com.huyhieu.coffee_go.ui.theme.utils.type.size
import com.huyhieu.coffee_go.ui.theme.utils.type.textAlign

@Preview
@Composable
fun AuthHeader(modifier: Modifier = Modifier, text: String = "Text") {
    Column(
        modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SpacerVertical()
        LogoApp(
            modifier = Modifier.size(120.dp), padding = 20.dp
        )
        SpacerVertical()
        Text(
            text = text,
            color = TextBlack,
            style = FontStyle.SemiBold.size(30.sp),
        )
    }
}

@Preview
@Composable
fun AuthFooter(
    modifier: Modifier = Modifier,
    textQuestion: String = "Don't have a account?",
    textAction: String = " Sign up ",
    onSignUp: () -> Unit = {},
) {
    Box(
        modifier = modifier, contentAlignment = Alignment.BottomCenter
    ) {
        val annotatedString = buildAnnotatedString {
            withStyle(style = SpanStyle(color = TextNormal)) {
                append(textQuestion)
            }
            pushStringAnnotation(textAction, "http://google.com")
            withStyle(
                style = SpanStyle(brush = BrushStyle.GradientPrimary_Horizontal),
            ) {
                append(textAction)
            }
        }
        ClickableText(
            text = annotatedString,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            style = FontStyle.Regular.textAlign(TextAlign.Center),
        ) { offset ->
            annotatedString.getStringAnnotations(
                tag = textAction,
                start = offset,
                end = offset,
            ).firstOrNull()?.let { result ->
                onSignUp.invoke()
            }
        }
    }
}

@Preview
@Composable
fun AuthWithSocial(
    modifier: Modifier = Modifier,
    onFacebook: () -> Unit = {},
    onGoogle: () -> Unit = {},
) {
    Column(
        modifier = modifier
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(horizontal = 20.dp),
        ) {
            HorizontalDivider(color = Line)
            Text(
                text = "or continue with",
                modifier = Modifier
                    .background(Color.White)
                    .padding(horizontal = 10.dp),
            )
        }
        SpacerVertical(32.dp)
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            ButtonIcon(
                drawableRes = R.drawable.ic_facebook, text = "", modifier = Modifier.width(90.dp)
            ) {
                onFacebook.invoke()
            }
            SpacerHorizontal(8.dp)
            ButtonIcon(
                drawableRes = R.drawable.ic_google, text = "", modifier = Modifier.width(90.dp)
            ) {
                onGoogle.invoke()
            }
        }
    }
}