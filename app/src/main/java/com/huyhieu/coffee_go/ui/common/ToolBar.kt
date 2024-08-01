package com.huyhieu.coffee_go.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.huyhieu.coffee_go.ui.theme.actionBarSize
import com.huyhieu.coffee_go.ui.theme.utils.type.FontStyle
import com.huyhieu.coffee_go.ui.theme.utils.type.size
import com.huyhieu.coffee_go.uitls.heightStatusBar

@Preview
@Composable
fun AppToolbar(
    modifier: Modifier = Modifier,
    iconLeft: ImageVector = Icons.AutoMirrored.Rounded.ArrowBack,
    title: String = "",
    onLeftClick: () -> Unit = {},
    isFitStatusBar: Boolean = false,
    contentRight: @Composable RowScope.() -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .then(
                if (isFitStatusBar) {
                    Modifier.statusBarsPadding()
                } else {
                    Modifier
                }
            )
            .height(actionBarSize),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                iconLeft,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .clickable(onClick = onLeftClick)
                    .padding(10.dp),
            )
            contentRight()
        }
        if (title.isNotEmpty()) {
            Text(
                text = title,
                style = FontStyle.Bold.size(24.sp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 70.dp),
                textAlign = TextAlign.Center,
            )
        }
    }
}