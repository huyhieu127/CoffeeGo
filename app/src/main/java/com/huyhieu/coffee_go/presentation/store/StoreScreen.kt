package com.huyhieu.coffee_go.presentation.store

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForwardIos
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.huyhieu.coffee_go.ui.clickableNoneRipple
import com.huyhieu.coffee_go.ui.common.AnimatedContentFadeIn
import com.huyhieu.coffee_go.ui.common.AppHorizontalDivider
import com.huyhieu.coffee_go.ui.common.AppLogo
import com.huyhieu.coffee_go.ui.common.CircleLoading
import com.huyhieu.coffee_go.ui.common.SpacerHorizontal
import com.huyhieu.coffee_go.ui.gradientStyle
import com.huyhieu.coffee_go.ui.theme.GrayBg
import com.huyhieu.coffee_go.ui.theme.Orange
import com.huyhieu.coffee_go.ui.theme.Primary
import com.huyhieu.coffee_go.ui.theme.PrimaryLight
import com.huyhieu.coffee_go.ui.theme.TextNormal
import com.huyhieu.coffee_go.ui.theme.edgeSize
import com.huyhieu.coffee_go.ui.theme.utils.type.FontStyle
import com.huyhieu.coffee_go.ui.theme.utils.type.size
import com.huyhieu.coffee_go.uitls.imageRequestUrl
import com.huyhieu.domain.common.ResultState

@Composable
fun StoreScreen(
    modifier: Modifier = Modifier, vm: StoreVM = hiltViewModel()
) {
    StoreUi(
        modifier = modifier,
        uiState = vm.uiState,
        onAction = vm::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreUi(
    modifier: Modifier,
    uiState: StoreUiState,
    onAction: (StoreAction) -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    AnimatedContentFadeIn(targetState = uiState.listForYou) {
        if (it is ResultState.Success) {
            val listCoffee = it.data
            Scaffold(modifier = modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection), topBar = {
                TopAppBar(
                    scrollBehavior = scrollBehavior,
                    title = {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .statusBarsPadding()
                                .padding(horizontal = edgeSize),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            AppLogo(size = 32.dp)
                            Text(text = "Store", style = FontStyle.Medium.size(32.sp))
                            Icon(
                                Icons.Rounded.Search,
                                contentDescription = null,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    },
                )

            }

            ) {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(
                            top = it.calculateTopPadding() + 8.dp, bottom = 0.dp
                        )
                        .padding(horizontal = edgeSize)

                ) {
                    StoreTabUi(uiState.tabSelected) { index ->
                        onAction.invoke(StoreAction.TabSelected(index))
                    }

                    LazyColumn(
                        modifier = Modifier.padding(top = 8.dp),
                        contentPadding = PaddingValues(bottom = 8.dp)
                    ) {
                        items(listCoffee) { item ->
                            StoreItemUi(
                                urlThumbnail = item.imageUrl,
                                name = item.name,
                                region = item.region,
                                rating = "4.8",
                                numberOfReviews = "2500"
                            )
                            AppHorizontalDivider()
                        }
                    }
                }
            }
        } else {
            CircleLoading(modifier = Modifier.fillMaxSize())
        }
    }

}

@Composable
fun StoreTabUi(
    tabSelected: Int,
    onTabSelected: (Int) -> Unit = {}
) {
    val listTab = listOf("For you", "Favorites")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(GrayBg, RoundedCornerShape(8.dp)),
        horizontalArrangement = Arrangement.spacedBy(
            edgeSize, Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        listTab.forEachIndexed { index, name ->
            if (index == tabSelected) {
                Box(
                    modifier = Modifier
                        .weight(1F)
                        .fillMaxHeight()
                        .gradientStyle(RoundedCornerShape(8.dp))
                        .clickableNoneRipple {
                            onTabSelected(index)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = name,
                        color = Color.White,
                        style = FontStyle.Bold,
                    )
                }
            } else {
                Box(
                    modifier = Modifier
                        .weight(1F)
                        .fillMaxHeight()
                        .clickableNoneRipple {
                            onTabSelected(index)
                        },
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = name,
                        style = FontStyle.Bold,
                    )
                }
            }
        }


    }
}

@Composable
fun StoreItemUi(
    urlThumbnail: String = "",
    name: String = "",
    region: String = "",
    rating: String = "",
    numberOfReviews: String = ""
) {
    var colorsPalette by remember {
        mutableStateOf(listOf(PrimaryLight, PrimaryLight))
    }
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            imageRequestUrl(url = urlThumbnail) { colorsPalette = it }.build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .size(80.dp)
                .gradientStyle(
                    RoundedCornerShape(size = 8.dp),
                    brush = Brush.linearGradient(colors = colorsPalette)
                )
        )
        Column(
            Modifier
                .weight(1F)
                .padding(start = 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = name, style = FontStyle.SemiBold.size(18.sp))
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
                    text = region,
                    style = FontStyle.Medium,
                    color = TextNormal,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    Icons.Rounded.Star,
                    contentDescription = null,
                    tint = Orange,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    modifier = Modifier.padding(start = 2.dp, top = 2.dp),
                    text = "4.8",
                    style = FontStyle.Medium,
                )
                Text(
                    modifier = Modifier.padding(start = 2.dp, top = 2.dp),
                    text = "(2.5k reviews)",
                    style = FontStyle.Regular,
                    color = TextNormal
                )
            }
        }
        Icon(
            Icons.AutoMirrored.Rounded.ArrowForwardIos,
            contentDescription = null,
            modifier = Modifier.size(16.dp),
        )
    }
}
