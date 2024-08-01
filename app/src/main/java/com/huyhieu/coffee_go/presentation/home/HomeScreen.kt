package com.huyhieu.coffee_go.presentation.home

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.huyhieu.coffee_go.presentation.basket.navigateToBasket
import com.huyhieu.coffee_go.presentation.order_detail.navigateToOrderDetail
import com.huyhieu.coffee_go.uitls.localContext

private const val TAG = "HomeScreen"

@Preview(showBackground = true)
@Composable
private fun HomeUiPreview() {
    HomeUi(
        state = HomeUiState(
            toolbar = Toolbar(
                greetings = "Good morning!", name = "Cristiano Ronaldo", isBadgeVisible = true
            ),
        )
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    viewModel: HomeVM = hiltViewModel(),
) {
    val context = localContext
    HomeUi(
        modifier = modifier,
        state = viewModel.uiState,
        onAction = { homeNavigate(it, navController, viewModel, context) },
    )
}

@Composable
private fun HomeUi(
    modifier: Modifier = Modifier,
    state: HomeUiState,
    onAction: (HomeAction) -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        ToolBarUi(
            state = state,
            onAction = onAction,
        )
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = if (state.isHaveBasket) 120.dp else 0.dp)
            ) {
                item {
                    BannerUi(
                        state = state,
                        onAction = onAction,
                    )
                }
                item {
                    NearbyShopUi(
                        state = state,
                        onAction = onAction,
                    )
                }
                item {
                    PopularMenuUi(
                        state = state,
                        onAction = onAction,
                    )
                }
            }
            HomeBasketStatusUi(
                state = state,
                onAction = onAction,
            )
        }
    }
}


private fun homeNavigate(
    action: HomeAction,
    navController: NavController,
    viewModel: HomeVM,
    context: Context,
) {
    when (action) {
        HomeAction.AvatarClick -> {

        }

        HomeAction.NotificationClick -> viewModel.actionNotification {
            Toast.makeText(context, "Notification", Toast.LENGTH_SHORT).show()
        }

        is HomeAction.BannerClick -> {
            Toast.makeText(context, "Banner ${action.banner.id}", Toast.LENGTH_SHORT).show()
        }

        HomeAction.NearbyShopViewAllClick -> {

        }

        is HomeAction.NearbyShopItemClick -> {

        }

        is HomeAction.PopularMenuItemClick -> {
            navController.navigateToOrderDetail(
                coffeeId = action.coffee.id,
                order = null,
            )
        }

        HomeAction.PopularMenuViewAllClick -> {

        }

        HomeAction.CheckBasketClick -> {
            navController.navigateToBasket()
        }
    }
}