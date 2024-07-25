package com.huyhieu.coffee_go.screens.home

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.huyhieu.coffee_go.R
import com.huyhieu.coffee_go.ui.common.ShimmerEffect
import com.huyhieu.coffee_go.uitls.localContext
import com.huyhieu.domain.entity.Coffee
import com.huyhieu.domain.common.ResponseState
import com.huyhieu.libs.logDebug

private const val TAG = "HomeScreen"

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeVM: HomeVM = hiltViewModel(),
) {
    val context = localContext
    val homeUiState = homeVM.homeUiState.collectAsState().value
    val bannerUiState = homeVM.bannerUiState.collectAsState().value
    HomeUi(
        modifier = modifier,
        homeUiState = homeUiState,
        bannerUiState = bannerUiState,
        actionAvatarClick = {},
        actionNotificationClick = {
            Toast.makeText(context, "Notification", Toast.LENGTH_SHORT).show()
            homeVM.actionNotification()
        },
        onActionBannerClick = {
            Toast.makeText(context, "Banner ${it.id}", Toast.LENGTH_SHORT).show()
        },
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
        ), bannerUiState = ResponseState.Loading()
    )
}

@Composable
private fun HomeUi(
    modifier: Modifier = Modifier,
    homeUiState: HomeUiState,
    bannerUiState: ResponseState<List<Coffee>>,
    actionAvatarClick: () -> Unit = {},
    actionNotificationClick: () -> Unit = {},
    onActionBannerClick: (Banner) -> Unit = {}
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
            when (bannerUiState) {
                is ResponseState.Error -> {
                    Toast.makeText(localContext, bannerUiState.error, Toast.LENGTH_SHORT).show()
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
    }
}

