package com.huyhieu.coffee_go.ui.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.huyhieu.coffee_go.R
import com.huyhieu.coffee_go.ui.clickableNoneRipple
import com.huyhieu.coffee_go.ui.theme.Primary
import com.huyhieu.coffee_go.ui.theme.TextBlack
import com.huyhieu.coffee_go.ui.theme.utils.type.FontStyle
import com.huyhieu.coffee_go.ui.theme.utils.type.size
import com.huyhieu.coffee_go.ui.tintGradientStyle

@Preview
@Composable
fun AppCheckbox(
    modifier: Modifier = Modifier,
    text: String = "",
    onCheckedChange: (Boolean) -> Unit = {},
    content: @Composable (() -> Unit)? = null
) {
    var isChecked by remember {
        mutableStateOf(false)
    }
    var icon by remember {
        mutableIntStateOf(R.drawable.ic_checkbox)
    }
    LaunchedEffect(isChecked) {
        icon = if (isChecked) R.drawable.ic_checkbox_checked else R.drawable.ic_checkbox
    }
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
                .tintGradientStyle()
                .align(Alignment.Top)
                .clickableNoneRipple {
                    isChecked = !isChecked
                    onCheckedChange.invoke(isChecked)
                },
            tint = Primary
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