package com.huyhieu.coffee_go.screens.home

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.huyhieu.coffee_go.R
import com.huyhieu.coffee_go.ui.common.ShimmerEffect
import com.huyhieu.coffee_go.ui.common.SpacerVertical
import com.huyhieu.coffee_go.uitls.localContext
import com.huyhieu.domain.common.ResponseState
import com.huyhieu.domain.entity.Coffee

private const val TAG = "HomeScreen"

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    vm: HomeVM = hiltViewModel(),
    onNavigateToViewAllOrderDetail: () -> Unit,
    onNavigateToOrderDetail: (Coffee) -> Unit,
) {
    val context = localContext
    val homeUiState = vm.homeUiState.collectAsState().value
    val bannerUiState = vm.bannerUiState.collectAsState().value
    val nearbyShopUiState = vm.nearbyShopUiState.collectAsState().value
    val popularMenuUiState = vm.popularMenuUiState.collectAsState().value
    HomeUi(
        modifier = modifier,
        homeUiState = homeUiState,
        bannerUiState = bannerUiState,
        nearbyShopUiState = nearbyShopUiState,
        popularMenuUiState = popularMenuUiState,
        actionAvatarClick = {},
        actionNotificationClick = {
            Toast.makeText(context, "Notification", Toast.LENGTH_SHORT).show()
            vm.actionNotification()
        },
        onActionBannerClick = {
            Toast.makeText(context, "Banner ${it.id}", Toast.LENGTH_SHORT).show()
        },
        onViewAllClick = onNavigateToViewAllOrderDetail,
        onItemClick = onNavigateToOrderDetail,
    )
}

@Preview(showBackground = true)
@Composable
private fun HomeUiPreview() {
    HomeUi(
        homeUiState = HomeUiState(
            toolbar = Toolbar(
                greetings = "Good morning!", name = "Cristiano Ronaldo", isBadgeVisible = true
            ), banners = listOf(
                Banner(bannerRes = R.drawable.coffee_banner),
                Banner(bannerRes = R.drawable.coffee_banner),
                Banner(bannerRes = R.drawable.coffee_banner),
            )
        ),
        bannerUiState = ResponseState.Loading(),
        nearbyShopUiState = ResponseState.Loading(),
        popularMenuUiState = ResponseState.Loading(),
    )
}

@Composable
private fun HomeUi(
    modifier: Modifier = Modifier,
    homeUiState: HomeUiState,
    bannerUiState: ResponseState<List<Coffee>>,
    nearbyShopUiState: ResponseState<List<Coffee>>,
    popularMenuUiState: ResponseState<List<Coffee>>,
    actionAvatarClick: () -> Unit = {},
    actionNotificationClick: () -> Unit = {},
    onActionBannerClick: (Banner) -> Unit = {},
    onViewAllClick: () -> Unit = {},
    onItemClick: (Coffee) -> Unit = {},
) {
    Scaffold(modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            ShimmerEffect(
                isLoading = homeUiState.toolbar.name.isEmpty(),
                contentLoading = {
                    ToolBarShimmerUi()
                },
            ) {
                ToolBarUi(
                    toolbar = homeUiState.toolbar,
                    actionAvatarClick = actionAvatarClick,
                    actionNotificationClick = actionNotificationClick
                )
            }
            LazyColumn(
                Modifier.fillMaxSize()
            ) {
                item {
                    when (bannerUiState) {
                        is ResponseState.Error -> {
                            Toast.makeText(localContext, bannerUiState.error, Toast.LENGTH_SHORT)
                                .show()
                        }

                        is ResponseState.Loading -> {
                            BannerShimmerUi()
                        }

                        is ResponseState.Success -> {
                            runCatching {
                                bannerUiState.data.map { coffee ->
                                    Banner(id = coffee.idStr, bannerUrl = coffee.imageUrl)
                                }
                            }.onSuccess { banners ->
                                BannerUi(
                                    banners = banners,
                                    onActionBannerClick = onActionBannerClick,
                                )
                            }
                        }
                    }
                }
                item {
                    when (nearbyShopUiState) {
                        is ResponseState.Error -> {
                            Toast.makeText(
                                localContext,
                                nearbyShopUiState.error,
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        is ResponseState.Loading -> {
                            BannerShimmerUi()
                        }

                        is ResponseState.Success -> {
                            NearbyShopUi(
                                title = "Nearby Shop",
                                nearbyShops = nearbyShopUiState.data,
                                onActionViewAllClick = {

                                },
                            )
                        }
                    }
                }
                item {
                    when (popularMenuUiState) {
                        is ResponseState.Error -> {
                            Toast.makeText(
                                localContext,
                                popularMenuUiState.error,
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }

                        is ResponseState.Loading -> {
                            BannerShimmerUi()
                        }

                        is ResponseState.Success -> {
                            SpacerVertical(12.dp)
                            PopularMenuUi(
                                title = "Popular Menu",
                                popularMenuList = popularMenuUiState.data,
                                onViewAllClick = onViewAllClick,
                                onItemClick = onItemClick
                            )
                        }
                    }
                }
            }
        }
    }
}

