package com.huyhieu.coffee_go.presentation.basket

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Checklist
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.huyhieu.coffee_go.ui.common.AnimatedContentFadeIn
import com.huyhieu.coffee_go.ui.common.AppToolbar
import com.huyhieu.coffee_go.ui.common.CircleLoading
import com.huyhieu.coffee_go.ui.theme.shadowSize
import com.huyhieu.domain.entity.Order

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
    onItemClick: (order: Order) -> Unit = {},
) {
    BasketUi(
        modifier = modifier,
        state = viewModel.uiState,
        onAction = {
            when (it) {
                BasketAction.CloseClick -> onCloseScreen()
                is BasketAction.ItemClick -> {
                    onItemClick(it.order)
                }

                else -> Unit
            }
            viewModel.onAction(it)
        },
    )
}

@Composable
fun BasketUi(
    modifier: Modifier = Modifier,
    state: BasketUiState = BasketUiState(),
    onAction: (BasketAction) -> Unit = {},
) {
    AnimatedContentFadeIn(targetState = state.isLoading) { isLoading ->
        if (isLoading) {
            CircleLoading(modifier = modifier.fillMaxSize())
        } else {
            Column {
                AppToolbar(
                    modifier = modifier
                        .shadow(shadowSize)
                        .background(Color.White),
                    isFitStatusBar = true,
                    title = "Basket",
                    onLeftClick = { onAction(BasketAction.CloseClick) },
                ) {
                    androidx.compose.animation.AnimatedVisibility(
                        visible = state.orders.isNotEmpty(),
                        enter = fadeIn(),
                        exit = fadeOut(),
                    ) {
                        Icon(
                            Icons.Rounded.Checklist.takeIf {
                                !state.isShowCheckbox
                            } ?: Icons.Rounded.Close,
                            contentDescription = null,
                            modifier = Modifier
                                .clip(CircleShape)
                                .clickable {
                                    onAction(BasketAction.ShowCheckboxClick(state.isShowCheckbox.not()))
                                }
                                .size(50.dp)
                                .padding(10.dp),
                        )
                    }
                }
                Box(modifier = Modifier.fillMaxSize()) {
                    BasketListUi(
                        state = state,
                        onAction = onAction,
                    )

                    BasketControllerUi(
                        state = state,
                        onAction = onAction,
                    )
                }

            }
        }
    }
}
