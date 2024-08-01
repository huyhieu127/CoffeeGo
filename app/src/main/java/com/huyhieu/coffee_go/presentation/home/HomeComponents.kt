package com.huyhieu.coffee_go.presentation.home

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForwardIos
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.huyhieu.coffee_go.R
import com.huyhieu.coffee_go.ui.clickableNoneRipple
import com.huyhieu.coffee_go.ui.common.AnimatedContentFadeIn
import com.huyhieu.coffee_go.ui.common.ShimmerEffect
import com.huyhieu.coffee_go.ui.common.SpacerHorizontal
import com.huyhieu.coffee_go.ui.common.SpacerVertical
import com.huyhieu.coffee_go.ui.gradientStyle
import com.huyhieu.coffee_go.ui.theme.GrayBorderIcon
import com.huyhieu.coffee_go.ui.theme.Orange
import com.huyhieu.coffee_go.ui.theme.Primary
import com.huyhieu.coffee_go.ui.theme.PrimaryLight
import com.huyhieu.coffee_go.ui.theme.TextBlack
import com.huyhieu.coffee_go.ui.theme.TextNormal
import com.huyhieu.coffee_go.ui.theme.actionBarSize
import com.huyhieu.coffee_go.ui.theme.alpha
import com.huyhieu.coffee_go.ui.theme.cornerSize
import com.huyhieu.coffee_go.ui.theme.edgeSize
import com.huyhieu.coffee_go.ui.theme.shadowSize
import com.huyhieu.coffee_go.ui.theme.utils.type.FontStyle
import com.huyhieu.coffee_go.ui.theme.utils.type.size
import com.huyhieu.coffee_go.uitls.localContext
import com.huyhieu.coffee_go.uitls.screenWidth
import com.huyhieu.domain.common.ResultState
import com.huyhieu.domain.common.findData
import com.huyhieu.domain.entity.Coffee
import com.huyhieu.libs.formatDollar
import kotlinx.coroutines.delay
import kotlin.math.absoluteValue

private const val TAG = "HomeComponents"

@Preview(showBackground = true)
@Composable
private fun BannerUiPreview() {
    BannerUi()
}

@Composable
fun BannerUi(
    state: HomeUiState = HomeUiState(), onAction: (HomeAction) -> Unit = {}
) {
    when (val bannerUiState = state.banner) {
        is ResultState.Error -> {
            Toast.makeText(localContext, bannerUiState.error, Toast.LENGTH_SHORT).show()
        }

        is ResultState.Loading -> {
            BannerShimmerUi()
        }

        is ResultState.Success -> {
            runCatching {
                bannerUiState.data.map { coffee ->
                    Banner(id = coffee.idStr, bannerUrl = coffee.imageUrl)
                }
            }.onSuccess { banners ->
                if (banners.isNotEmpty()) {
                    val pagerState = rememberPagerState(initialPage = banners.getInitialPage(),
                        pageCount = { Int.MAX_VALUE })

                    LaunchedEffect(pagerState.settledPage) {
                        delay(3000)
                        val nextPage = if (pagerState.currentPage == Int.MAX_VALUE * (2 / 3)) {
                            (Int.MAX_VALUE / 3)
                        } else {
                            pagerState.currentPage + 1
                        }
                        pagerState.animateScrollToPage(nextPage)
                    }
                    Box(
                        modifier = Modifier
                            .padding(vertical = edgeSize)
                            .fillMaxWidth(),
                    ) {
                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier.align(alignment = Alignment.Center),
                        ) { pageIndex ->
                            banners.getOrNull(pageIndex % banners.size)?.let { banner ->
                                val pageOffset = pagerState.getOffsetDistanceInPages(pageIndex)
                                val scaleBg = 7F - (pageOffset.absoluteValue * 5F)
                                val scaleImage = (pageOffset.absoluteValue * 6F) + 0.9F
                                val minusRatioHeight = (pageOffset.absoluteValue * 0.7F)
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = edgeSize)
                                        .aspectRatio(4 / 2F)
                                        .clickableNoneRipple {
                                            onAction(HomeAction.BannerClick(banner))
                                        }
                                        .graphicsLayer {
                                            rotationY = -(pageOffset * 10F)
                                        }
                                        .aspectRatio(4 / (2F - minusRatioHeight))
                                        .clip(RoundedCornerShape(cornerSize))
                                        .background(PrimaryLight),
                                ) {
                                    AsyncImage(
                                        model = banner.bannerUrl.ifEmpty { R.drawable.coffee_banner },
                                        contentDescription = "",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .scale(scaleBg)
                                            .blur(
                                                radiusX = 10.dp,
                                                radiusY = 10.dp,
                                                edgeTreatment = BlurredEdgeTreatment(
                                                    RoundedCornerShape(8.dp)
                                                )
                                            )
                                    )
                                    AsyncImage(model = banner.bannerUrl.ifEmpty { R.drawable.coffee_banner },
                                        contentDescription = "",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .scale(scaleImage)
                                            .graphicsLayer {
                                                rotationZ = 20F - (40F * pageOffset.absoluteValue)
                                            })
                                }
                            }
                        }
                        if (banners.size > 1) {
                            Row(
                                Modifier
                                    .align(Alignment.BottomCenter)
                                    .padding(vertical = 4.dp)
                                    //.background(BlackOpacity10, RoundedCornerShape(50))
                                    .padding(3.dp), horizontalArrangement = Arrangement.Center
                            ) {
                                runCatching {
                                    repeat(banners.size) { iteration ->
                                        val isCurrent =
                                            (pagerState.currentPage % banners.size) == iteration
                                        val color =
                                            if (isCurrent) Primary else PrimaryLight.alpha(0.5F)
                                        val width = if (isCurrent) 16.dp else 6.dp
                                        Box(
                                            modifier = Modifier
                                                .padding(horizontal = 4.dp)
                                                .clip(CircleShape)
                                                .background(color)
                                                .width(width)
                                                .height(6.dp)
                                        )
                                    }
                                }
                            }

                        }

                    }
                }
            }
        }
    }

}

private fun List<Banner>.getInitialPage() = runCatching {
    var initPage = Int.MAX_VALUE / 2
    while (initPage % size != 0) {
        initPage++
    }
    initPage
}.getOrDefault(0)

@Preview(showBackground = true)
@Composable
private fun ToolbarUiPreview() {
    ToolBarUi(
        state = HomeUiState(
            toolbar = Toolbar(
                greetings = "Good morning!", name = "Cristiano Ronaldo", isBadgeVisible = true
            )
        )
    )
}

@Composable
fun ToolBarUi(
    state: HomeUiState = HomeUiState(),
    onAction: (HomeAction) -> Unit = {},
) {
    AnimatedContentFadeIn(
        targetState = state.toolbar,
        modifier = Modifier
            .fillMaxWidth()
            .shadow(shadowSize)
            .background(Color.White)
            .statusBarsPadding()
    ) { toolbar ->
        ShimmerEffect(
            isLoading = toolbar.name.isEmpty(),
            contentLoading = {
                ToolBarShimmerUi()
            },
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(actionBarSize)
                    .padding(horizontal = edgeSize),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    Icons.Rounded.AccountCircle,
                    contentDescription = null,
                    modifier = Modifier
                        .size(42.dp)
                        .clickableNoneRipple(onClick = { onAction(HomeAction.AvatarClick) }),
                )
                Column(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .weight(1f),
                ) {
                    Text(
                        toolbar.greetings,
                        style = FontStyle.Light.size(fontSize = 14.sp),
                    )
                    SpacerVertical(4.dp)
                    Text(
                        toolbar.name,
                        style = FontStyle.Medium.size(18.sp),
                    )
                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(42.dp)
                        .border(
                            width = 1.dp, color = GrayBorderIcon, shape = CircleShape
                        )
                        .padding(4.dp)
                        .clickableNoneRipple(onClick = { onAction(HomeAction.NotificationClick) }),
                ) {
                    Icon(
                        Icons.Outlined.Notifications,
                        contentDescription = null,
                        Modifier.size(30.dp),
                    )
                    this@Row.AnimatedVisibility(
                        visible = toolbar.isBadgeVisible,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 6.dp, end = 8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .background(Color.Red, CircleShape)
                                .size(6.dp)
                        )
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun TitleContainerUiPreview() {
    TitleContainerUi(
        title = "Nearby Shop",
        onViewAllClick = {},
    )
}

@Composable
fun TitleContainerUi(
    title: String,
    onViewAllClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = edgeSize, end = edgeSize - 8.dp)
            .padding(vertical = 6.dp),
    ) {
        Text(
            text = title,
            style = FontStyle.Bold.size(20.sp),
            modifier = Modifier.weight(1F),
        )
        Box(
            modifier = Modifier.clickableNoneRipple(onViewAllClick)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "View All",
                    style = FontStyle.SemiBold.size(12.sp),
                    color = Primary,
                )
                Icon(
                    Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                    contentDescription = null,
                    tint = Primary,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NearbyShopUiPreview() {
    NearbyShopUi(
        state = HomeUiState(
            popularMenu = ResultState.Success(
                listOf(
                    Coffee(
                        name = "Caffely Astoria Aromas - Ho Chi Minh", region = "Ho Chi Minh"
                    ),
                    Coffee(
                        name = "Caffely Astoria", region = "Ho Chi Minh"
                    ),
                    Coffee(
                        name = "Caffely Astoria Aromas", region = "Ho Chi Minh"
                    ),
                )
            )
        ),
    )
}

@Composable
fun NearbyShopUi(
    state: HomeUiState = HomeUiState(),
    onAction: (HomeAction) -> Unit = {},
) {
    when (val nearbyShopUiState = state.nearbyShop) {
        is ResultState.Error -> {
            Toast.makeText(
                localContext, nearbyShopUiState.error, Toast.LENGTH_SHORT
            ).show()
        }

        is ResultState.Loading -> {
            BannerShimmerUi()
        }

        is ResultState.Success -> {
            val nearbyShops = nearbyShopUiState.data
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                TitleContainerUi(
                    title = "Nearby Shop",
                    onViewAllClick = { onAction(HomeAction.NearbyShopViewAllClick) },
                )
                LazyRow(
                    contentPadding = PaddingValues(horizontal = edgeSize - 8.dp),
                ) {
                    items(nearbyShops) { item ->
                        NearbyShopItemUi(item)
                    }
                }
            }
        }
    }
}

@Composable
private fun NearbyShopItemUi(coffee: Coffee) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .width(150.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1F)
                .clip(RoundedCornerShape(cornerSize))
                .background(PrimaryLight)
        ) {
            AsyncImage(
                model = coffee.imageUrl.ifEmpty { R.drawable.coffee_banner },
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
                model = coffee.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .scale(2.4F),
            )

            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .background(Color.Black.alpha(0.4F), RoundedCornerShape(6.dp))
                    .padding(2.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SpacerHorizontal(2.dp)
                Icon(
                    Icons.Rounded.Star,
                    contentDescription = null,
                    tint = Orange,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    modifier = Modifier.padding(start = 2.dp, top = 2.dp, end = 6.dp),
                    text = "4.8",
                    style = FontStyle.Bold,
                    color = Color.White,
                )
            }
        }

        Text(
            modifier = Modifier
                .padding(vertical = 6.dp)
                .fillMaxWidth(),
            //.heightIn((18.dp) * 2)
            text = coffee.name,
            style = FontStyle.SemiBold.copy(lineHeight = 18.sp),
            color = TextBlack,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                Icons.Rounded.LocationOn,
                contentDescription = null,
                tint = Primary,
                modifier = Modifier.size(16.dp)
            )
            SpacerHorizontal(2.dp)
            Text(
                text = coffee.region,
                style = FontStyle.SemiBold.size(12.sp),
                color = TextNormal,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PopularMenuUiPreview() {
    PopularMenuUi(
        state = HomeUiState(
            popularMenu = ResultState.Success(
                listOf(
                    Coffee(
                        name = "Caffely Astoria Aromas - Ho Chi Minh", region = "Ho Chi Minh"
                    ),
                    Coffee(
                        name = "Caffely Astoria", region = "Ho Chi Minh"
                    ),
                    Coffee(
                        name = "Caffely Astoria Aromas", region = "Ho Chi Minh"
                    ),
                )
            )
        ),
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PopularMenuUi(
    state: HomeUiState = HomeUiState(),
    onAction: (HomeAction) -> Unit = {},
) {
    when (val popularMenuUiState = state.popularMenu) {
        is ResultState.Error -> {
            Toast.makeText(
                localContext, popularMenuUiState.error, Toast.LENGTH_SHORT
            ).show()
        }

        is ResultState.Loading -> {
            BannerShimmerUi()
        }

        is ResultState.Success -> {
            val popularMenuList = popularMenuUiState.data
            SpacerVertical(12.dp)
            Column {
                TitleContainerUi(
                    title = "Popular Menu",
                    onViewAllClick = { onAction(HomeAction.PopularMenuViewAllClick) },
                )
                FlowRow(
                    maxItemsInEachRow = 2,
                    modifier = Modifier.padding(horizontal = edgeSize - 8.dp),
                ) {
                    (popularMenuList).forEach { item ->
                        PopularMenuItemUi(
                            coffee = item,
                            onItemClick = { onAction(HomeAction.PopularMenuItemClick(it)) },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PopularMenuItemUi(
    coffee: Coffee,
    onItemClick: (Coffee) -> Unit = {},
) {
    val widthImage = (screenWidth - (edgeSize * 3)) / 2 // 3 - Space, 2 - items in row
    Column(modifier = Modifier
        .padding(8.dp)
        .width(widthImage)
        .clickableNoneRipple {
            onItemClick.invoke(coffee)
        }) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1F)
                .clip(RoundedCornerShape(cornerSize))
                .background(PrimaryLight)
        ) {
            AsyncImage(
                model = coffee.imageUrl.ifEmpty { R.drawable.coffee_banner },
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
                model = coffee.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .scale(1.45F),
            )
        }

        Text(
            modifier = Modifier
                .padding(vertical = 6.dp)
                .fillMaxWidth(),
            //.heightIn((18.dp) * 2)
            text = coffee.name,
            style = FontStyle.SemiBold.copy(lineHeight = 18.sp),
            color = TextBlack,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Text(
            text = "$${coffee.price}",
            style = FontStyle.SemiBold.size(14.sp),
            color = Primary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BoxScope.HomeBasketStatusUi(
    state: HomeUiState = HomeUiState(),
    onAction: (HomeAction) -> Unit = {},
) {
    AnimatedVisibility(
        modifier = Modifier.align(Alignment.BottomCenter),
        visible = !state.basket.findData().isNullOrEmpty(),
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
        if (state.basket is ResultState.Success) {
            val list = state.basket.data ?: emptyList()
            val count = list.size
            val totalPrice = list.sumOf { it.totalPrice }.formatDollar()
            Row(
                modifier = Modifier
                    .padding(edgeSize)
                    .clip(RoundedCornerShape(8.dp))
                    .shadow(8.dp)
                    .gradientStyle(RoundedCornerShape(8.dp))
                    .padding(horizontal = edgeSize, vertical = 10.dp)
                    .fillMaxWidth()
                    .clickableNoneRipple {
                        onAction(HomeAction.CheckBasketClick)
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier
                        .weight(1F)
                        .padding(end = edgeSize),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_brasket),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                        SpacerHorizontal(4.dp)
                        Text(
                            text = "Total $count item${"(s)".takeIf { count > 1 } ?: ""}",
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 5.dp),
                            lineHeight = 12.sp,
                        )
                    }
                    SpacerVertical(2.dp)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "$",
                            style = FontStyle.Bold.size(18.sp),
                            color = Color.White,
                        )
                        Text(
                            text = totalPrice,
                            style = FontStyle.Bold.size(20.sp),
                            color = Color.White,
                        )
                    }
                }
                SpacerHorizontal(8.dp)
                Text(
                    text = "Check now",
                    color = Color.White,
                    style = FontStyle.Medium.size(20.sp),
                )
                SpacerHorizontal(6.dp)
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowForwardIos,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )

            }
        }
    }
}