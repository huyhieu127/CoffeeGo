package com.huyhieu.coffee_go.ui.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.huyhieu.coffee_go.R
import com.huyhieu.coffee_go.ui.gradientStyle
import com.huyhieu.coffee_go.ui.lightStyle
import com.huyhieu.coffee_go.ui.outlineStyle
import com.huyhieu.coffee_go.ui.theme.Primary
import com.huyhieu.coffee_go.ui.theme.TextBlack
import com.huyhieu.coffee_go.ui.theme.utils.brush.BrushStyle
import com.huyhieu.coffee_go.ui.theme.utils.type.FontStyle
import com.huyhieu.coffee_go.ui.theme.utils.type.size
import com.huyhieu.coffee_go.uitls.takeIfInspectionMode

@Preview
@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    isShowPreview: Boolean = true,
    text: String = "Normal Button".takeIfInspectionMode({ isShowPreview }) { "" },
    onClick: (() -> Unit) = {},
    content: @Composable BoxScope.() -> Unit = {
        Text(
            text = text,
            color = TextBlack,
            style = FontStyle.Medium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(14.dp)
        )
    },
) {
    Box(
        modifier = modifier
            .clickable {
                onClick.invoke()
            },
        contentAlignment = Alignment.Center,
    ) {
        content()
    }

}

@Preview
@Composable
fun AppPrimaryButton(
    modifier: Modifier = Modifier,
    isShowPreview: Boolean = true,
    text: String = "Normal Button".takeIfInspectionMode({ isShowPreview }) { "" },
    fontSize: TextUnit = 16.sp,
    shape: Shape = RoundedCornerShape(50),
    brush: Brush = BrushStyle.GradientPrimary_Horizontal,
    shadowColor: Color = Primary,
    contentPadding: PaddingValues = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
    onClick: (() -> Unit) = {},
    content: @Composable BoxScope.() -> Unit = {
        Text(
            text = text,
            color = Color.White,
            style = FontStyle.Medium.size(fontSize),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(14.dp)
        )
    },
) {
    Box(
        modifier = modifier
            .gradientStyle(shape, brush, shadowColor = shadowColor)
            .clickable {
                onClick.invoke()
            },
        contentAlignment = Alignment.Center,
    ) {
        content()
    }

}

@Preview
@Composable
fun AppLightButton(
    modifier: Modifier = Modifier,
    isShowPreview: Boolean = true,
    text: String = "Light Button".takeIfInspectionMode({ isShowPreview }) { "" },
    fontSize: TextUnit = 16.sp,
    shape: Shape = RoundedCornerShape(50),
    contentPadding: PaddingValues = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
    onClick: (() -> Unit) = {},
    content: @Composable BoxScope.() -> Unit = {
        Text(
            text = text,
            color = Primary,
            style = FontStyle.Medium.copy(
                fontSize = fontSize,
                brush = BrushStyle.GradientPrimary_Horizontal
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(12.dp)
        )
    },
) {
    Box(
        modifier = modifier
            .lightStyle(shape)
            .clickable {
                onClick.invoke()
            },
        contentAlignment = Alignment.Center,
    ) {
        content()
    }

}

@Preview
@Composable
fun AppIconButton(
    modifier: Modifier = Modifier,
    isShowPreview: Boolean = true,
    text: String = "ButtonIcon".takeIfInspectionMode({ isShowPreview }) { "" },
    @DrawableRes drawableRes: Int = R.drawable.ic_google,
    fontSize: TextUnit = 16.sp,
    shape: Shape = RoundedCornerShape(14.dp),
    contentPadding: PaddingValues = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
    onClick: (() -> Unit) = {},
) {
    Row(
        modifier = modifier
            .outlineStyle(shape)
            .clickable {
                onClick.invoke()
            }
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            //imageVector = ImageVector.vectorResource(id = drawableRes),
            painter = painterResource(id = drawableRes),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = Color.Unspecified
        )
        if (text.isNotEmpty()) {
            SpacerHorizontal(12.dp)
            Text(
                text = text,
                color = TextBlack,
                style = FontStyle.Medium.size(fontSize),
                textAlign = TextAlign.Center,
            )
        }
    }

}