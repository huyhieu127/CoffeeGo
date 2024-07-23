package com.huyhieu.coffee_go.ui.common

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.huyhieu.coffee_go.ui.theme.TextNormal
import com.huyhieu.coffee_go.ui.theme.utils.brush.BrushStyle
import com.huyhieu.coffee_go.ui.theme.utils.type.FontStyle
import com.huyhieu.coffee_go.ui.theme.utils.type.textAlign

@Preview
@Composable
fun TextPrivacyPolicy(
    modifier: Modifier = Modifier,
    onCheckedChange: (Boolean) -> Unit = {},
    onTermConditions: (url: String) -> Unit = {},
    onPrivacyPolicy: (url: String) -> Unit = {},
) {
    val context = LocalContext.current
    var isRemember by remember {
        mutableStateOf(false)
    }
    val textQuestion1 = "By continuing, I agree to the"
    val termsCondition = " terms conditions"
    val textQuestion2 = ", and"
    val privacyPolicy = " privacy policy"
    val textQuestion3 = "."

    AppCheckbox(
        modifier = modifier,
        onCheckedChange = {
            isRemember = it
            onCheckedChange(it)
        },
    ) {
        val annotatedString = buildAnnotatedString {
            withStyle(style = SpanStyle(color = TextNormal)) {
                append(textQuestion1)
            }
            pushStringAnnotation(termsCondition, "http://google.com")
            withStyle(
                style = SpanStyle(brush = BrushStyle.GradientPrimary_Horizontal),
            ) {
                append(termsCondition)
            }
            pop()
            withStyle(style = SpanStyle(color = TextNormal)) {
                append(textQuestion2)
            }
            pushStringAnnotation(privacyPolicy, "http://facebook.com")
            withStyle(
                style = SpanStyle(brush = BrushStyle.GradientPrimary_Horizontal),
            ) {
                append(privacyPolicy)
            }
            pop()
            withStyle(style = SpanStyle(color = TextNormal)) {
                append(textQuestion3)
            }
        }
        SpacerHorizontal(8.dp)
        ClickableText(
            text = annotatedString,
            modifier = Modifier
                .fillMaxWidth(),
            style = FontStyle.Regular.textAlign(TextAlign.Start),
        ) { offset ->
            annotatedString.getStringAnnotations(
                tag = termsCondition,
                start = offset,
                end = offset,
            ).firstOrNull()?.let { result ->
                onTermConditions.invoke(result.item)
                val webIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(result.item)
                )
                context.startActivity(webIntent)
            }
            annotatedString.getStringAnnotations(
                tag = privacyPolicy,
                start = offset,
                end = offset,
            ).firstOrNull()?.let { result ->
                onPrivacyPolicy.invoke(result.item)
                val webIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(result.item)
                )
                context.startActivity(webIntent)
            }
        }
    }
}