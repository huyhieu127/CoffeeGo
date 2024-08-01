package com.huyhieu.coffee_go.ui.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.huyhieu.coffee_go.R
import com.huyhieu.coffee_go.ui.clickableNoneRipple
import com.huyhieu.coffee_go.ui.theme.TextBlack
import com.huyhieu.coffee_go.ui.theme.utils.brush.BrushStyle
import com.huyhieu.coffee_go.ui.theme.utils.type.FontStyle
import com.huyhieu.coffee_go.ui.theme.utils.type.size
import com.huyhieu.coffee_go.ui.tintGradientStyle

@Preview
@Composable
fun AppCheckbox(
    modifier: Modifier = Modifier,
    isChecked: Boolean = false,
    text: String = "",
    size: Dp = 20.dp,
    brush: Brush = BrushStyle.GradientPrimary_Horizontal,
    onCheckedChange: (Boolean) -> Unit = {},
    content: @Composable (() -> Unit)? = null
) {
    val icon = if (isChecked) R.drawable.ic_checkbox_checked else R.drawable.ic_checkbox
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .size(size)
                .tintGradientStyle(brush)
                .align(Alignment.Top)
                .clickableNoneRipple {
                    onCheckedChange.invoke(isChecked.not())
                },
        )
        if (content != null) {
            content()
        }
        if (content == null && text.isNotEmpty()) {
            Text(
                text = text,
                color = TextBlack,
                style = FontStyle.Medium.size(14.sp),
                modifier = Modifier.padding(start = 8.dp)
            )
        }

    }
}