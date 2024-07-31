package com.huyhieu.coffee_go.screens.order_detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Coffee
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material.icons.rounded.RadioButtonChecked
import androidx.compose.material.icons.rounded.RadioButtonUnchecked
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.huyhieu.coffee_go.R
import com.huyhieu.coffee_go.ui.borderSelectStyle
import com.huyhieu.coffee_go.ui.clickableNoneRipple
import com.huyhieu.coffee_go.ui.common.AnimatedContentNumberJump
import com.huyhieu.coffee_go.ui.common.AppHorizontalDivider
import com.huyhieu.coffee_go.ui.common.AppPrimaryButton
import com.huyhieu.coffee_go.ui.common.SpacerHorizontal
import com.huyhieu.coffee_go.ui.common.SpacerVertical
import com.huyhieu.coffee_go.ui.theme.Primary
import com.huyhieu.coffee_go.ui.theme.PrimaryLight
import com.huyhieu.coffee_go.ui.theme.PrimaryRed
import com.huyhieu.coffee_go.ui.theme.TextDescription
import com.huyhieu.coffee_go.ui.theme.cornerSize
import com.huyhieu.coffee_go.ui.theme.edgeSize
import com.huyhieu.coffee_go.ui.theme.utils.brush.BrushStyle
import com.huyhieu.coffee_go.ui.theme.utils.type.FontStyle
import com.huyhieu.coffee_go.ui.theme.utils.type.size
import com.huyhieu.coffee_go.ui.tintGradientStyle
import com.huyhieu.coffee_go.ui.tintSelectStyle
import com.huyhieu.coffee_go.uitls.screenWidth
import com.huyhieu.domain.entity.OrderOption
import com.huyhieu.domain.entity.OrderOptional
import com.huyhieu.libs.append


@Composable
fun OrderDetailOptionalUi(
    title: String,
    itemSelected: OrderOptional? = null,
    isExpand: Boolean = false,
    listOptionals: List<OrderOptional>,
    onExpandClick: () -> Unit = {},
    onItemSelected: (OrderOptional) -> Unit = {},
) {
    val rotate by animateFloatAsState(
        targetValue = if (isExpand) 0F else 90F, label = "Rotate"
    )
    Column {
        AppHorizontalDivider(Modifier.padding(edgeSize))
        Row(
            modifier = Modifier
                .padding(horizontal = edgeSize, vertical = 12.dp)
                .clickableNoneRipple(onExpandClick),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = title, style = FontStyle.Bold.size(18.sp), modifier = Modifier.weight(1F)
            )
            AnimatedVisibility(
                visible = !isExpand,
                enter = fadeIn() + slideInVertically(
                    initialOffsetY = {
                        it / 2
                    },
                ),
                exit = fadeOut() + slideOutVertically(
                    targetOffsetY = {
                        it / 2
                    },
                ),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = itemSelected?.name ?: "",
                        style = FontStyle.Regular.size(18.sp),
                        modifier = Modifier,
                        color = Primary
                    )
                    SpacerHorizontal(4.dp)
                    Text(
                        text = "(+ $${itemSelected?.extraPrice ?: 0})",
                        style = FontStyle.Light,
                        modifier = Modifier,
                        color = TextDescription
                    )
                }
            }
            Icon(Icons.Rounded.KeyboardArrowUp,
                contentDescription = null,
                modifier = Modifier
                    .tintGradientStyle()
                    .graphicsLayer {
                        rotationZ = rotate
                    })
        }

        AnimatedVisibility(visible = isExpand) {
            Column {
                listOptionals.forEach { item ->
                    val isSelected = itemSelected?.id == item.id
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = edgeSize - 4.dp, vertical = 4.dp)
                            .borderSelectStyle(isSelected)
                            .padding(12.dp)
                            .clickableNoneRipple {
                                onItemSelected(item)
                            },
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = item.name,
                            modifier = Modifier.weight(1F),
                        )
                        SpacerHorizontal(6.dp)
                        Text(
                            text = "+ $${item.extraPrice}",
                            color = TextDescription,
                        )
                        SpacerHorizontal(6.dp)
                        Icon(Icons.Rounded.RadioButtonChecked.takeIf { isSelected }
                            ?: Icons.Rounded.RadioButtonUnchecked,
                            contentDescription = null,
                            modifier = Modifier.tintGradientStyle())
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun OrderDetailOptionUi(
    title: String,
    isDescription: Boolean = false,
    orderOption: List<OrderOption>,
    itemSelected: OrderOption?,
    onItemSelected: (OrderOption) -> Unit = {},
    icon: @Composable ColumnScope.(id: Int) -> Unit,
) {
    Column {
        AppHorizontalDivider(Modifier.padding(edgeSize))
        Text(
            text = title,
            style = FontStyle.Bold.size(18.sp),
            modifier = Modifier.padding(horizontal = edgeSize, vertical = 12.dp),
        )
        FlowRow(
            maxItemsInEachRow = 3,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = edgeSize - 8.dp),
        ) {
            (orderOption).forEach { item ->
                val widthImage = ((screenWidth - edgeSize) / 3)  // 3 - Space, 2 - items in row
                val isSelected = itemSelected?.id == item.id
                Column(
                    modifier = Modifier
                        .width(widthImage)
                        .padding(8.dp)
                        .borderSelectStyle(isSelected, 1.5.dp)
                        .clickableNoneRipple {
                            onItemSelected(item)
                        }
                        .padding(vertical = 28.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    icon(item.id)
                    SpacerVertical(12.dp)
                    Text(
                        text = item.name.takeIf { item.shortName.isEmpty() }
                            ?: item.name.append("(${item.shortName})"),
                        style = FontStyle.SemiBold,
                    )
                    if (isDescription) {
                        val description =
                            ("Free".takeIf { item.extraPrice == 0.0 } ?: "+ $${item.extraPrice}")
                        SpacerVertical(12.dp)
                        Text(
                            text = description, style = FontStyle.Regular, color = TextDescription
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun OrderDetailQuantityUi(
    price: Double,
    quantity: Int,
    onMinusClick: () -> Unit = {},
    onPlusClick: () -> Unit = {},
) {
    Row(
        Modifier
            .padding(horizontal = edgeSize)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$$price",
            style = FontStyle.Regular.size(18.sp),
            modifier = Modifier
                .weight(1F)
                .padding(end = edgeSize)
        )

        AnimatedVisibility(
            visible = quantity > 1,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            ButtonControlUi(
                icon = Icons.Rounded.Remove, onClick = onMinusClick
            )
        }
        AnimatedContentNumberJump(targetState = quantity.toDouble()) {
            Text(
                text = "${it.toInt()}",
                textAlign = TextAlign.Center,
                style = FontStyle.SemiBold.size(20.sp),
                modifier = Modifier
                    .padding(8.dp)
                    .widthIn(40.dp),
            )
        }
        AnimatedVisibility(
            visible = quantity < 5,
        ) {
            ButtonControlUi(
                icon = Icons.Rounded.Add,
                onClick = onPlusClick,
            )
        }
    }
}

@Composable
fun OrderDetailTotalPriceUi(
    totalPrice: Double = 0.0,
    onAddToBasketClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .background(Color.White)
            .navigationBarsPadding()
            .padding(edgeSize)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier
                .weight(1F)
                .padding(end = edgeSize),
        ) {
            Text(text = "Total price")
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "$",
                    style = FontStyle.Bold.size(24.sp),
                )
                AnimatedContentNumberJump(targetState = totalPrice) {
                    Text(
                        text = "$it",
                        style = FontStyle.Bold.size(24.sp),
                    )
                }
            }
        }
        AppPrimaryButton(
            onClick = onAddToBasketClick,
            modifier = Modifier
                .widthIn(180.dp),
            text = "Add to Basket",
        )
    }
}

@Composable
fun OrderDetailToolBarUi(
    isFavorite: Boolean = false,
    onCloseClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onShareClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .background(Color.White)
            .statusBarsPadding()
            .padding(horizontal = edgeSize - 8.dp)
    ) {
        Icon(
            Icons.Rounded.Close,
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .size(28.dp)
                .clickableNoneRipple(onCloseClick),
        )
        Spacer(modifier = Modifier.weight(1F))
        Icon(
            Icons.Rounded.Favorite.takeIf { isFavorite } ?: Icons.Rounded.FavoriteBorder,
            tint = PrimaryRed.takeIf { isFavorite } ?: Color.Black,
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .size(28.dp)
                .clickableNoneRipple(onFavoriteClick),
        )
        Icon(
            Icons.Rounded.Share,
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .size(28.dp)
                .clickableNoneRipple(onShareClick),
        )

    }
}

@Composable
fun OrderDetailHeaderUi(
    imageUrl: String,
    name: String,
    description: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(edgeSize),
    ) {
        Box(
            modifier = Modifier
                .aspectRatio(1F)
                .clip(RoundedCornerShape(cornerSize))
                .background(PrimaryLight),
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .scale(7F)
                    .blur(
                        radiusX = 10.dp,
                        radiusY = 10.dp,
                        edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(8.dp))
                    )
            )
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .scale(1.2F)
                    .fillMaxSize()
            )
        }
        SpacerVertical(16.dp)
        Text(text = name, style = FontStyle.Bold.size(24.sp))
        SpacerVertical(12.dp)
        Text(text = description, style = FontStyle.Regular, color = TextDescription)
    }
}

@Composable
fun OrderDetailIconSizeUi(
    id: Int,
    isSelect: Boolean = false,
) {
    val modifier = when (id) {
        1 -> Modifier
            .height(32.dp)
            .padding(top = 8.dp)
            .size(24.dp)

        2 -> Modifier
            .height(32.dp)
            .padding(top = 4.dp)
            .size(26.dp)

        3 -> Modifier
            .height(32.dp)
            .size(28.dp)

        else -> {
            Modifier
                .height(32.dp)
                .size(28.dp)
        }
    }
    Image(
        painterResource(id = R.drawable.ic_coffee_size),
        contentDescription = null,
        modifier = modifier.tintSelectStyle(isSelect),
        contentScale = ContentScale.FillBounds,
    )
}

@Composable
fun OrderDetailIconAvailableInUi(
    id: Int,
    isSelect: Boolean = false,
) {
    val modifier = Modifier
        .size(24.dp)
        .tintSelectStyle(isSelect)
    when (id) {
        1 -> Icon(
            painterResource(id = R.drawable.ic_coffee),
            contentDescription = null,
            modifier = modifier
        )

        2 -> Icon(
            painterResource(id = R.drawable.ic_coffee_outline),
            contentDescription = null,
            modifier = modifier
        )

        else -> Icon(
            Icons.Rounded.Coffee, contentDescription = null, modifier = modifier
        )
    }
}

@Composable
private fun ButtonControlUi(
    icon: ImageVector,
    onClick: () -> Unit = {},
) {
    Icon(
        icon,
        contentDescription = null,
        modifier = Modifier
            .border(
                width = 1.5.dp,
                brush = BrushStyle.GradientPrimary_Horizontal,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(6.dp)
            .clickableNoneRipple(onClick),
        tint = Primary,
    )
}

