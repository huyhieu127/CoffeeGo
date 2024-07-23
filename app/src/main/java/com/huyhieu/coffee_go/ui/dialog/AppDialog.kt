package com.huyhieu.coffee_go.ui.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.huyhieu.coffee_go.R
import com.huyhieu.coffee_go.ui.common.AppThumbnail
import com.huyhieu.coffee_go.ui.common.SpacerVertical
import com.huyhieu.coffee_go.ui.common.button.ButtonPrimary
import com.huyhieu.coffee_go.ui.common.button.ButtonPrimaryLight
import com.huyhieu.coffee_go.ui.theme.Primary
import com.huyhieu.coffee_go.ui.theme.TextBlack
import com.huyhieu.coffee_go.ui.theme.utils.brush.BrushStyle
import com.huyhieu.coffee_go.ui.theme.utils.type.FontStyle
import com.huyhieu.libs.loremIpsumText


@Preview
@Composable
fun AlertDialogPreview() {
    AppDialog(
        title = loremIpsumText(20),
        content = loremIpsumText(),
        textAccept = "Accept",
        textCancel = "Cancel",
        thumbnail = R.drawable.ic_done
    )
}

@Composable
fun AppDialog(
    isShow: MutableState<Boolean> = mutableStateOf(true),
    isCancelable: Boolean = true,
    onDismissRequest: () -> Unit = { },

    thumbnail: Int = 0,
    brush: Brush = BrushStyle.GradientPrimary_Horizontal,
    contentTop: (@Composable ColumnScope.() -> Unit)? = null,

    title: String = "",
    content: String = "",

    textAccept: String = "",
    onAccept: (() -> Unit) = { },
    textCancel: String = "",
    onCancel: (() -> Unit) = { },
    contentFooter: (@Composable ColumnScope.() -> Unit)? = null
) {
    if (isShow.value) {
        Dialog(
            onDismissRequest = {
                if (isCancelable) {
                    isShow.value = false
                    onDismissRequest()
                }
            },
        ) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(30.dp))
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                if (contentTop != null) {
                    contentTop()
                } else if (thumbnail != 0) {
                    AppThumbnail(
                        modifier = Modifier.size(100.dp),
                        drawableRes = thumbnail,
                        brush = brush,
                        padding = 20.dp
                    )
                }
                if (title.isNotEmpty()) {
                    if (thumbnail != 0) {
                        SpacerVertical()
                    }
                    Text(
                        text = title,
                        style = FontStyle.SemiBold.copy(fontSize = 20.sp, brush = brush),
                        color = Primary,
                        textAlign = TextAlign.Center
                    )
                }
                if (content.isNotEmpty()) {
                    if (title.isNotEmpty()) {
                        SpacerVertical()
                    }
                    Text(
                        text = content,
                        style = FontStyle.Regular,
                        color = TextBlack,
                        textAlign = TextAlign.Center
                    )
                }
                if (contentFooter != null) {
                    contentFooter()
                } else {
                    if (textAccept.isNotEmpty() || textCancel.isNotEmpty()) {
                        SpacerVertical(24.dp)
                    }
                    if (textAccept.isNotEmpty()) {
                        SpacerVertical(12.dp)
                        ButtonPrimary(
                            text = textAccept,
                            modifier = Modifier.fillMaxWidth(),
                            onClick = onAccept
                        )
                    }
                    if (textCancel.isNotEmpty()) {
                        SpacerVertical(12.dp)
                        ButtonPrimaryLight(
                            text = textCancel,
                            modifier = Modifier.fillMaxWidth(),
                            onClick = onCancel
                        )
                    }
                }
            }
        }
    }
}