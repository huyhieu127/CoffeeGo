package com.huyhieu.coffee_go.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.huyhieu.coffee_go.R
import com.huyhieu.coffee_go.ui.common.button.clickableNoneRipple
import com.huyhieu.coffee_go.ui.theme.BlackOpacity10
import com.huyhieu.coffee_go.ui.theme.Primary
import com.huyhieu.coffee_go.ui.theme.PrimaryLight
import com.huyhieu.coffee_go.ui.theme.actionBarSize
import com.huyhieu.coffee_go.ui.theme.alpha
import com.huyhieu.coffee_go.ui.theme.cornerSize
import com.huyhieu.coffee_go.ui.theme.edgeSize
import com.huyhieu.coffee_go.ui.theme.utils.type.FontStyle
import com.huyhieu.coffee_go.ui.theme.utils.type.size
import kotlinx.coroutines.delay

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
            ) { pageIndex ->
                banners.getOrNull(pageIndex % banners.size)?.let { banner ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = edgeSize)
                            .aspectRatio(4 / 2F)
                            .clip(RoundedCornerShape(cornerSize))
                            .background(PrimaryLight)
                            .clickableNoneRipple {
                                onActionBannerClick(banner)
                            },
                    ) {
                        AsyncImage(
                            model = banner.bannerUrl.ifEmpty { R.drawable.coffee_banner },
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
            if (banners.size > 1) {
                Row(
                    Modifier
                        .align(Alignment.BottomCenter)
                        .padding(vertical = 4.dp)
                        .background(BlackOpacity10, RoundedCornerShape(50))
                        .padding(3.dp),
                    horizontalArrangement = Arrangement.Center
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
                style = FontStyle.Medium,
            )
        }

        Box(
            contentAlignment = Alignment.TopStart,
            modifier = Modifier
                .size(40.dp)
                .border(
                    width = 1.dp, color = Color.White, shape = CircleShape
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