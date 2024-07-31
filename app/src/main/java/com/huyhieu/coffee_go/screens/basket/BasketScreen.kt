package com.huyhieu.coffee_go.screens.basket

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.huyhieu.coffee_go.ui.clickableNoneRipple
import com.huyhieu.coffee_go.ui.common.AppToolbar
import com.huyhieu.coffee_go.ui.theme.edgeSize
import com.huyhieu.domain.common.ResultState

@Preview(showBackground = true)
@Composable
private fun BasketScreenPreview() {
    BasketScreen()
}

@Composable
fun BasketScreen(
    modifier: Modifier = Modifier,
    viewModel: BasketVM = hiltViewModel(),
    onCloseScreen: () -> Unit = {},
) {
    BasketUi(
        modifier = modifier,
        state = viewModel.uiState,
        onAction = {
            when (it) {
                BasketAction.CloseClick -> onCloseScreen()
                is BasketAction.ItemClick -> {

                }
            }
            //viewModel.onAction(it)
        },
    )
}

@Composable
fun BasketUi(
    modifier: Modifier = Modifier,
    state: BasketUiState = BasketUiState(),
    onAction: (BasketAction) -> Unit = {},
) {
    Scaffold(modifier) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            AppToolbar(
                title = "Basket"
            ) {
                onAction(BasketAction.CloseClick)
            }
            when (state.orders) {
                is ResultState.Success -> {
                    LazyColumn(
                        state = rememberLazyListState(),
                    ) {
                        val result = state.orders.data ?: emptyList()
                        items(result) { item ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = edgeSize,
                                        vertical = 8.dp,
                                    )
                                    .clickableNoneRipple {
                                        onAction(BasketAction.ItemClick(item))
                                    },
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = item.id.toString())
                                Text(text = item.quantity.toString())
                                Text(text = "$${item.totalPrice}")
                            }
                        }
                    }
                }

                else -> Unit
            }
        }
    }
}
