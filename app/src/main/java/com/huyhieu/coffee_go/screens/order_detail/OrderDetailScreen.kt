package com.huyhieu.coffee_go.screens.order_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.huyhieu.coffee_go.navigation.route.MainDest
import com.huyhieu.coffee_go.ui.common.AnimatedContentFadeIn
import com.huyhieu.coffee_go.ui.common.CircleLoading

@Preview(showBackground = true)
@Composable
fun OrderDetailScreenPreview() {
    OrderDetailScreen()
}

@Composable
fun OrderDetailScreen(
    viewModel: OrderDetailVM = hiltViewModel(),
    orderDetailDest: MainDest.OrderDetail = MainDest.OrderDetail(),
    onCloseScreen: () -> Unit = {},
) {
    val coffee = orderDetailDest.getCoffee()
    val orderId = orderDetailDest.orderId
    LaunchedEffect(orderId, coffee) {
        viewModel.loadData(orderId, coffee)
    }
    OrderDetailUi(
        state = viewModel.uiState,
        onAction = {
            when (it) {
                OrderDetailAction.CloseClick -> onCloseScreen()
                OrderDetailAction.AddToBasketClick -> viewModel.onAddToBasketClick {
                    onCloseScreen()
                }

                else -> Unit
            }
            viewModel.onAction(it)
        },
    )
}

@Composable
fun OrderDetailUi(
    modifier: Modifier = Modifier,
    state: OrderDetailUiState = OrderDetailUiState(),
    onAction: (OrderDetailAction) -> Unit = {},
) {
    AnimatedContentFadeIn(targetState = state.coffee) { coffee ->
        if (coffee == null) {
            CircleLoading(modifier = modifier.fillMaxSize())
        } else {
            Column(
                modifier = modifier.fillMaxSize()
                //.padding(it)
            ) {
                OrderDetailToolBarUi(
                    isFavorite = state.isFavorite,
                    onCloseClick = { onAction(OrderDetailAction.CloseClick) },
                    onFavoriteClick = { onAction(OrderDetailAction.FavoriteClick) },
                    onShareClick = { onAction(OrderDetailAction.ShareClick) }
                )
                LazyColumn(
                    state = rememberLazyListState(),
                    modifier = Modifier.weight(1F),
                    contentPadding = PaddingValues(bottom = 50.dp),
                ) {
                    item {
                        OrderDetailHeaderUi(coffee.imageUrl, coffee.name, coffee.description)
                    }
                    item {
                        OrderDetailQuantityUi(
                            price = coffee.price,
                            quantity = state.quantity,
                            onMinusClick = { onAction(OrderDetailAction.MinusQuantityClick) },
                            onPlusClick = { onAction(OrderDetailAction.PlusQuantityClick) },
                        )
                    }
                    item {
                        OrderDetailOptionUi(
                            title = "Available in",
                            orderOption = coffee.type,
                            itemSelected = state.availableInSelected,
                            onItemSelected = { onAction(OrderDetailAction.AvailableInClick(it)) }
                        ) {
                            OrderDetailIconAvailableInUi(
                                id = it,
                                isSelect = it == state.availableInSelected?.id,
                            )
                        }
                    }
                    item {
                        OrderDetailOptionUi(
                            title = "Size",
                            isDescription = true,
                            orderOption = coffee.size,
                            itemSelected = state.sizeSelected,
                            onItemSelected = { onAction(OrderDetailAction.SizeClick(it)) }
                        ) {
                            OrderDetailIconSizeUi(
                                id = it,
                                isSelect = it == state.sizeSelected?.id,
                            )
                        }
                    }
                    item {
                        OrderDetailOptionalUi(
                            title = "Flavor profile",
                            itemSelected = state.flavorProfileSelected,
                            isExpand = state.isExpandFlavorProfile,
                            listOptionals = coffee.flavorProfile,
                            onExpandClick = { onAction(OrderDetailAction.ExpandFlavorProfileClick) },
                            onItemSelected = {
                                onAction(
                                    OrderDetailAction.ItemSelectedFlavorProfile(
                                        it
                                    )
                                )
                            },
                        )
                    }
                    item {
                        OrderDetailOptionalUi(
                            title = "Grind option",
                            itemSelected = state.grindOptionSelected,
                            isExpand = state.isExpandGrindOption,
                            listOptionals = coffee.grindOption,
                            onExpandClick = { onAction(OrderDetailAction.ExpandGrindOptionClick) },
                            onItemSelected = { onAction(OrderDetailAction.ItemSelectedGrindOption(it)) },
                        )
                    }
                }
                OrderDetailTotalPriceUi(
                    totalPrice = state.totalPrice,
                    onAddToBasketClick = { onAction(OrderDetailAction.AddToBasketClick) }
                )
            }
        }
    }

}
