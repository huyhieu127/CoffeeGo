package com.huyhieu.coffee_go.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.huyhieu.coffee_go.R
import com.huyhieu.coffee_go.ui.common.SpacerHorizontal
import com.huyhieu.coffee_go.ui.common.button.clickableNoneRipple
import com.huyhieu.coffee_go.ui.theme.GrayBorder
import com.huyhieu.coffee_go.ui.theme.Orange
import com.huyhieu.coffee_go.ui.theme.Primary
import com.huyhieu.coffee_go.ui.theme.PrimaryLight
import com.huyhieu.coffee_go.ui.theme.TextBlack
import com.huyhieu.coffee_go.ui.theme.TextNormal
import com.huyhieu.coffee_go.ui.theme.actionBarSize
import com.huyhieu.coffee_go.ui.theme.alpha
import com.huyhieu.coffee_go.ui.theme.cornerSize
import com.huyhieu.coffee_go.ui.theme.edgeSize
import com.huyhieu.coffee_go.ui.theme.utils.type.FontStyle
import com.huyhieu.coffee_go.ui.theme.utils.type.size
import com.huyhieu.coffee_go.uitls.screenWidth
import com.huyhieu.domain.entity.Coffee
import kotlinx.coroutines.delay
import kotlin.math.absoluteValue

private const val TAG = "HomeComponents"

@Preview(showBackground = true)
@Composable
private fun BannerUiPreview() {
    BannerUi(
        banners = listOf(
            Banner(bannerRes = R.drawable.coffee_banner),
            Banner(bannerRes = R.drawable.coffee_banner),
            Banner(bannerRes = R.drawable.coffee_banner),
        ),
    )
}

@Composable
fun BannerUi(
    banners: List<Banner> = emptyList(), onActionBannerClick: (Banner) -> Unit = {}
) {
    if (banners.isNotEmpty()) {
        val pagerState = rememberPagerState(
            initialPage = banners.getInitialPage(),
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
                                onActionBannerClick(banner)
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
                                    edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(8.dp))
                                )
                        )
                        AsyncImage(
                            model = banner.bannerUrl.ifEmpty { R.drawable.coffee_banner },
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .scale(scaleImage)
                                .graphicsLayer {
                                    rotationZ = 20F - (40F * pageOffset.absoluteValue)
                                }
                        )
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
                            val isCurrent = (pagerState.currentPage % banners.size) == iteration
                            val color = if (isCurrent) Primary else PrimaryLight.alpha(0.5F)
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
        toolbar = Toolbar(
            greetings = "Good morning!", name = "Cristiano Ronaldo", isBadgeVisible = true
        )
    )
}

@Composable
fun ToolBarUi(
    toolbar: Toolbar,
    actionAvatarClick: () -> Unit = {},
    actionNotificationClick: () -> Unit = {},
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
                .clickableNoneRipple(onClick = actionAvatarClick),
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
                    width = 1.dp, color = GrayBorder, shape = CircleShape
                )
                .padding(4.dp)
                .clickableNoneRipple(onClick = actionNotificationClick),
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

@Preview(showBackground = true)
@Composable
private fun TitleContainerUiPreview() {
    TitleContainerUi(
        title = "Nearby Shop",
        onActionViewAllClick = {},
    )
}

@Composable
fun TitleContainerUi(
    title: String,
    onActionViewAllClick: () -> Unit,
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
            modifier = Modifier.clickableNoneRipple(onActionViewAllClick)
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
        title = "Nearby Shop",
        nearbyShops = listOf(
            Coffee(
                name = "Caffely Astoria Aromas - Ho Chi Minh", region = "Ho Chi Minh"
            ),
            Coffee(
                name = "Caffely Astoria", region = "Ho Chi Minh"
            ),
            Coffee(
                name = "Caffely Astoria Aromas", region = "Ho Chi Minh"
            ),
        ),
        onActionViewAllClick = {

        },
    )
}

@Composable
fun NearbyShopUi(
    title: String,
    nearbyShops: List<Coffee>,
    onActionViewAllClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        TitleContainerUi(
            title = title, onActionViewAllClick = onActionViewAllClick
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
                    modifier = Modifier
                        .size(24.dp)
                )
                Text(
                    modifier = Modifier
                        .padding(start = 2.dp, top = 2.dp, end = 6.dp),
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
        title = "Popular Menu",
        popularMenuList = listOf(
            Coffee(
                name = "Caffely Astoria Aromas - Ho Chi Minh", region = "Ho Chi Minh"
            ),
            Coffee(
                name = "Caffely Astoria", region = "Ho Chi Minh"
            ),
            Coffee(
                name = "Caffely Astoria Aromas", region = "Ho Chi Minh"
            ),
        ),
        onActionViewAllClick = {

        },
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PopularMenuUi(
    title: String,
    popularMenuList: List<Coffee>,
    onActionViewAllClick: () -> Unit,
) {
    Column {
        TitleContainerUi(
            title = title, onActionViewAllClick = onActionViewAllClick
        )
        FlowRow(
            maxItemsInEachRow = 2, modifier = Modifier.padding(horizontal = edgeSize - 8.dp)
        ) {
            (popularMenuList).forEach { item ->
                PopularMenuItemUi(item)
            }
        }
    }
}

@Composable
private fun PopularMenuItemUi(coffee: Coffee) {
    val widthImage = (screenWidth - (edgeSize * 3)) / 2 // 3 - Space, 2 - items in row
    Column(
        modifier = Modifier
            .padding(8.dp)
            .width(widthImage)
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
