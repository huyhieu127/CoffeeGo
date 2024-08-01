package com.huyhieu.coffee_go.presentation.basket

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.huyhieu.coffee_go.ui.borderColor
import com.huyhieu.coffee_go.ui.clickableNoneRipple
import com.huyhieu.coffee_go.ui.common.AppButton
import com.huyhieu.coffee_go.ui.common.AppCheckbox
import com.huyhieu.coffee_go.ui.common.AppPrimaryButton
import com.huyhieu.coffee_go.ui.common.SpacerHorizontal
import com.huyhieu.coffee_go.ui.common.SpacerVertical
import com.huyhieu.coffee_go.ui.theme.GrayBg
import com.huyhieu.coffee_go.ui.theme.PrimaryRed
import com.huyhieu.coffee_go.ui.theme.TextDescription
import com.huyhieu.coffee_go.ui.theme.edgeSize
import com.huyhieu.coffee_go.ui.theme.utils.brush.BrushStyle
import com.huyhieu.coffee_go.ui.theme.utils.type.FontStyle
import com.huyhieu.coffee_go.ui.theme.utils.type.size
import com.huyhieu.coffee_go.ui.tintGradientStyle
import com.huyhieu.domain.entity.Order
import com.huyhieu.libs.formatDollar


@Composable
fun BoxScope.BasketControllerUi(
    state: BasketUiState = BasketUiState(),
    onAction: (BasketAction) -> Unit = {},
) {
    AnimatedVisibility(
        visible = state.orders.isNotEmpty(),
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter),
    ) {
        AnimatedContent(
            targetState = state.isShowCheckbox,
            label = "AnimatedContent",
            transitionSpec = {
                fadeIn() + slideInVertically(
                    initialOffsetY = { it },
                ) togetherWith fadeOut() + slideOutVertically(
                    targetOffsetY = { it },
                )
            },
        ) { isShow ->
            if (isShow) {
                val ids = state.idsChecked() ?: emptyList()
                val text = when (ids.size) {
                    0 -> "Cancel"
                    1 -> "Delete ${ids.size} item"
                    else -> "Delete ${ids.size} items"
                }
                AppPrimaryButton(
                    text = text,
                    onClick = {
                        onAction(BasketAction.DeleteClick(ids))
                    },
                    brush = BrushStyle.GradientError_Horizontal,
                    shadowColor = PrimaryRed,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .padding(edgeSize),
                )
            } else {
                AppPrimaryButton(
                    text = "Checkout - $${state.orders.sumOf { it.totalPrice }.formatDollar()}",
                    onClick = {},
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .padding(edgeSize),
                )
            }
        }
    }
}

@Composable
fun BasketListUi(
    state: BasketUiState,
    onAction: (BasketAction) -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        if (state.orders.isNotEmpty()) {
            LazyColumn(
                contentPadding = PaddingValues(top = edgeSize),
                state = rememberLazyListState(),
                modifier = Modifier
                    .fillMaxSize()
                    .animateContentSize(),
            ) {
                val result = state.orders
                items(result) { item ->
                    val isChecked =
                        state.isShowCheckbox && (state.idsChecked()?.any { id -> id == item.id }
                            ?: false)
                    BasketItemUi(item = item,
                        isShowCheckbox = state.isShowCheckbox,
                        isChecked = isChecked,
                        onChangeCheckbox = { checked, orderId ->
                            onAction(BasketAction.ChangeCheckbox(checked, orderId))
                        },
                        onItemClick = {
                            onAction(BasketAction.ItemClick(it))
                        },
                        onDelete = {
                            onAction(BasketAction.DeleteClick(listOf(it.id)))
                        })
                }
            }
        } else {
            Text("Basket is empty!", style = FontStyle.Medium.size(20.sp))
        }
    }
}

@Composable
fun BasketItemUi(
    item: Order,
    isShowCheckbox: Boolean,
    isChecked: Boolean,
    onChangeCheckbox: (Boolean, Int) -> Unit,
    onItemClick: (order: Order) -> Unit,
    onDelete: (order: Order) -> Unit,
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(
            horizontal = edgeSize,
            vertical = 8.dp,
        )
        .borderColor(shape = RoundedCornerShape(20.dp))
        .clickableNoneRipple {
            onItemClick(item)
        }
        .padding(
            start = edgeSize,
            top = edgeSize,
            bottom = 2.dp,
            end = 4.dp,
        )) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .clip(shape = RoundedCornerShape(12.dp))
                    .background(GrayBg),
                contentAlignment = Alignment.BottomStart,
            ) {
                AsyncImage(
                    model = item.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                )
                androidx.compose.animation.AnimatedVisibility(
                    modifier = Modifier.padding(6.dp),
                    visible = isShowCheckbox,
                    enter = fadeIn()
                            + scaleIn(transformOrigin = TransformOrigin(0.0f, 1f)),
                    exit = fadeOut()
                            + scaleOut(transformOrigin = TransformOrigin(0.0f, 1f)),
                ) {
                    AppCheckbox(modifier = Modifier,
                        isChecked = isChecked,
                        size = 24.dp,
                        brush = BrushStyle.GradientError_Horizontal,
                        onCheckedChange = {
                            onChangeCheckbox(it, item.id)
                        })
                }
            }
            Column(
                modifier = Modifier
                    .weight(1F)
                    .padding(horizontal = 12.dp)
            ) {
                Text(
                    text = "(${item.quantity}x) ${item.name}",
                    style = FontStyle.SemiBold.size(18.sp),
                )
                SpacerVertical(8.dp)
                Text(
                    text = item.description,
                    color = TextDescription,
                    style = FontStyle.Medium.size(16.sp),
                    lineHeight = 16.sp,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.heightIn(max = (16 * 2).dp),
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Total:",
                style = FontStyle.Regular,
            )
            SpacerHorizontal(8.dp)
            Text(
                text = "$${item.totalPrice.formatDollar()}",
                style = FontStyle.SemiBold,
            )
            Spacer(Modifier.weight(1f))
            AppButton(
                onClick = {
                    onDelete(item)
                },
                modifier = Modifier
                    .width(80.dp)
                    .height(40.dp)
                    .clip(RoundedCornerShape(6.dp)),
            ) {
                Text(
                    text = "Delete",
                    style = FontStyle.Medium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.tintGradientStyle(BrushStyle.GradientError_Horizontal),
                )
            }

            AppButton(
                modifier = Modifier
                    .width(60.dp)
                    .height(40.dp)
                    .clip(RoundedCornerShape(6.dp)),
                onClick = {
                    onItemClick(item)
                },
            ) {
                Text(
                    text = "Edit",
                    style = FontStyle.Medium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.tintGradientStyle(BrushStyle.GradientPrimary_Horizontal),
                )
            }


        }
    }
}
