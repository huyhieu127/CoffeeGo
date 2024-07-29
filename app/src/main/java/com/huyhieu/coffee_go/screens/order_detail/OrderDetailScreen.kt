package com.huyhieu.coffee_go.screens.order_detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Coffee
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.huyhieu.coffee_go.R
import com.huyhieu.coffee_go.ui.common.AppHorizontalDivider
import com.huyhieu.coffee_go.ui.common.SpacerVertical
import com.huyhieu.coffee_go.ui.common.button.AppPrimaryButton
import com.huyhieu.coffee_go.ui.common.button.borderColor
import com.huyhieu.coffee_go.ui.common.button.borderGradient
import com.huyhieu.coffee_go.ui.common.button.clickableNoneRipple
import com.huyhieu.coffee_go.ui.theme.Primary
import com.huyhieu.coffee_go.ui.theme.PrimaryLight
import com.huyhieu.coffee_go.ui.theme.PrimaryRed
import com.huyhieu.coffee_go.ui.theme.TextNormal
import com.huyhieu.coffee_go.ui.theme.cornerSize
import com.huyhieu.coffee_go.ui.theme.edgeSize
import com.huyhieu.coffee_go.ui.theme.utils.brush.BrushStyle
import com.huyhieu.coffee_go.ui.theme.utils.type.FontStyle
import com.huyhieu.coffee_go.ui.theme.utils.type.size
import com.huyhieu.coffee_go.uitls.screenWidth
import com.huyhieu.domain.entity.Coffee
import com.huyhieu.domain.entity.Size
import com.huyhieu.libs.append

@Preview(showBackground = true)
@Composable
fun OrderDetailScreenPreview(modifier: Modifier = Modifier) {
    OrderDetailScreen(
        modifier = modifier,
        coffee = Coffee(
            name = "Coffee name", price = 19.0
        ),
    )
}

@Composable
fun OrderDetailScreen(
    modifier: Modifier = Modifier,
    vm: OrderDetailVM = hiltViewModel(),
    coffee: Coffee = Coffee(),
    onCloseScreen: () -> Unit = {},
) {
    OrderDetailUi(
        modifier = modifier,
        coffee = coffee,
        onCloseClick = onCloseScreen
    )
}

@Composable
fun OrderDetailUi(
    modifier: Modifier = Modifier,
    coffee: Coffee,
    onCloseClick: () -> Unit = {},
) {
    Scaffold(modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            OrderDetailToolBarUi(
                onCloseClick = onCloseClick
            )
            LazyColumn(
                Modifier.weight(1F)
            ) {
                item {
                    Image(
                        painterResource(id = R.drawable.coffee_banner),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .aspectRatio(1F)
                            .padding(edgeSize)
                            .clip(RoundedCornerShape(cornerSize))
                            .background(PrimaryLight),
                    )
                }
                item {
                    OrderDetailQuantityUi(
                        name = coffee.name,
                        price = coffee.price,
                    )
                }
                item {
                    OrderDetailAvailableInUi(coffee.type)
                }
                item {
                    OrderDetailSizeUi(coffee.size)
                }
            }
            OrderDetailTotalPriceUi()
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun OrderDetailSizeUi(
    size: List<Size>,
    onItemSelected: (Int) -> Unit = {},
) {
    Column {
        AppHorizontalDivider(Modifier.padding(edgeSize))
        Text(
            text = "Size",
            style = FontStyle.SemiBold,
            modifier = Modifier.padding(horizontal = edgeSize, vertical = 12.dp),
        )

        var itemSelected by remember { mutableIntStateOf(-1) }
        FlowRow(
            maxItemsInEachRow = 3,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = edgeSize - 8.dp),
        ) {
            (size).forEach { item ->
                SelectItem(
                    icon = {
                        val modifier = when (item.id) {
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
                            modifier = modifier,
                            contentScale = ContentScale.FillBounds
                        )
                    },
                    id = item.id,
                    name = item.name.append("(${item.shortName})"),
                    description = if (item.extraPrice == 0.0)
                        "Free"
                    else
                        "+${item.extraPrice}",
                    isSelect = itemSelected == item.id,
                ) {
                    itemSelected = it
                    onItemSelected(it)
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun OrderDetailAvailableInUi(
    type: List<String>,
    onItemSelected: (Int) -> Unit = {},
) {
    Column {
        AppHorizontalDivider(Modifier.padding(edgeSize))
        Text(
            text = "Available in",
            style = FontStyle.SemiBold,
            modifier = Modifier.padding(horizontal = edgeSize, vertical = 12.dp),
        )

        var itemSelected by remember { mutableIntStateOf(-1) }
        FlowRow(
            maxItemsInEachRow = 3,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = edgeSize - 8.dp),
        ) {
            (type).forEachIndexed { index, name ->
                SelectItem(
                    icon = {
                        when (name) {
                            "Hot" -> Icon(
                                painterResource(id = R.drawable.ic_coffee),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )

                            "Iced" -> Icon(
                                painterResource(id = R.drawable.ic_coffee_outline),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )

                            else -> Icon(
                                Icons.Rounded.Coffee,
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    },
                    id = index,
                    name = name,
                    isSelect = itemSelected == index,
                ) {
                    itemSelected = it
                    onItemSelected(it)
                }
            }
        }
    }
}

@Composable
fun OrderDetailQuantityUi(
    name: String = "Classic Brew",
    price: Double = 3.5,
    quantity: Int = 1
) {
    var count by remember { mutableIntStateOf(quantity) }
    var min by remember { mutableStateOf(true) }
    var max by remember { mutableStateOf(false) }
    Row(
        Modifier
            .padding(horizontal = edgeSize)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .weight(1F)
                .padding(end = edgeSize),
        ) {
            Text(
                text = name,
                style = FontStyle.SemiBold.size(20.sp),
            )
            Text(
                text = "$$price", style = FontStyle.Regular.size(18.sp)
            )
        }

        AnimatedVisibility(
            visible = !min,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            ButtonControlUi(Icons.Rounded.Remove) {
                if (count > 1) count--
                max = false
                min = count <= 1
            }
        }
        Text(
            text = "$count",
            textAlign = TextAlign.Center,
            style = FontStyle.SemiBold.size(20.sp),
            modifier = Modifier
                .padding(8.dp)
                .widthIn(40.dp),
        )
        AnimatedVisibility(
            visible = !max,

            ) {
            ButtonControlUi(Icons.Rounded.Add) {
                if (count < 5) count++
                min = false
                max = count >= 5
            }
        }
    }
}

@Composable
fun OrderDetailTotalPriceUi() {
    Row(
        Modifier
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
            Text(
                text = "$0",
                style = FontStyle.Bold,
            )
        }
        AppPrimaryButton(
            onClick = {},
            modifier = Modifier.widthIn(180.dp),
            text = "Add to Basket",
        )
    }
}

@Composable
fun OrderDetailToolBarUi(
    isFavorite: Boolean = false,
    onCloseClick: () -> Unit = {},
) {
    var isFilled by remember { mutableStateOf(isFavorite) }
    Row(
        modifier = Modifier.padding(edgeSize - 8.dp)
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
            Icons.Rounded.Favorite.takeIf { isFilled } ?: Icons.Rounded.FavoriteBorder,
            tint = PrimaryRed.takeIf { isFilled } ?: Color.Black,
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .size(28.dp)
                .clickableNoneRipple {
                    isFilled = isFilled.not()
                },
        )
        Icon(
            Icons.Rounded.Share,
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .size(28.dp),
        )

    }
}

@Composable
fun SelectItem(
    icon: @Composable ColumnScope.() -> Unit,
    id: Int,
    name: String,
    description: String? = "",
    isSelect: Boolean = false,
    onSelect: (Int) -> Unit = {},
) {
    val widthImage = ((screenWidth - edgeSize) / 3)  // 3 - Space, 2 - items in row
    Column(
        modifier = Modifier
            .width(widthImage)
            .padding(8.dp)
            .then(
                Modifier
                    .borderGradient(width = (1.5).dp)
                    .takeIf { isSelect } ?: Modifier.borderColor()
            )
            .padding(vertical = 28.dp)
            .clickableNoneRipple {
                onSelect(id)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        icon()
        SpacerVertical(12.dp)
        Text(
            text = name,
            style = FontStyle.SemiBold,
        )
        if (description?.isNotEmpty() == true) {
            SpacerVertical(12.dp)
            Text(
                text = description,
                style = FontStyle.Regular,
                color = TextNormal
            )
        }
    }
}

@Composable
fun ButtonControlUi(
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

