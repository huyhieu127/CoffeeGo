package com.huyhieu.coffee_go.screens

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.huyhieu.coffee_go.R
import com.huyhieu.coffee_go.navigation.route.BnbDest
import com.huyhieu.coffee_go.screens.home.HomeScreen
import com.huyhieu.coffee_go.screens.order.OrderScreen
import com.huyhieu.coffee_go.screens.order.ProfileScreen
import com.huyhieu.coffee_go.screens.store.StoreScreen
import com.huyhieu.coffee_go.ui.clickableNoneRipple
import com.huyhieu.coffee_go.ui.common.AnimatedContentFadeIn
import com.huyhieu.coffee_go.ui.common.SpacerVertical
import com.huyhieu.coffee_go.ui.theme.TextDescription
import com.huyhieu.coffee_go.ui.theme.utils.type.FontStyle
import com.huyhieu.coffee_go.ui.theme.utils.type.size
import com.huyhieu.coffee_go.ui.tintSelectStyle

data class NavigationBar(
    val title: String,
    val iconResourceId: Int,
    val badgeAmount: Int? = null,
    val destination: BnbDest = BnbDest.Home
)

private val navigationBars = listOf(
    NavigationBar(
        title = "Home",
        iconResourceId = R.drawable.ic_home,
        destination = BnbDest.Home,
    ),
    NavigationBar(
        title = "Store",
        iconResourceId = R.drawable.ic_store,
        destination = BnbDest.Store,
    ),
    NavigationBar(
        title = "Order",
        iconResourceId = R.drawable.ic_brasket,
        destination = BnbDest.Order,
    ),
    NavigationBar(
        title = "Profile",
        iconResourceId = R.drawable.ic_person,
        destination = BnbDest.Profile,
    ),
)

@Preview(showBackground = true)
@Composable
fun Bnb(
    modifier: Modifier = Modifier,
    appNavHostController: NavHostController = rememberNavController(),
) {
    val navBarController: NavHostController = rememberNavController()
    var tabSelected by rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            AppNavigationBar(navigationBars, tabSelected) { index, item ->
                navBarController.navigate(item.destination){
                    popUpTo(navBarController.graph.startDestinationId)
                }
            }
        },
    ) {
        NavHost(
            navController = navBarController,
            modifier = Modifier.padding(it),
            startDestination = BnbDest.Home
        ) {
            composable<BnbDest.Home> {
                tabSelected = 0
                HomeScreen(
                    navController = appNavHostController
                )
            }
            composable<BnbDest.Store> {
                tabSelected = 1
                StoreScreen()
            }
            composable<BnbDest.Order> {
                tabSelected = 2
                OrderScreen()
            }
            composable<BnbDest.Profile> {
                tabSelected = 3
                ProfileScreen()
            }
        }
    }
}

@Composable
fun AppNavigationBar(
    navigationBars: List<NavigationBar>,
    tabSelected: Int,
    onNavigateBarItemClick: (index: Int, item: NavigationBar) -> Unit,
) {
    Row(
        modifier = Modifier
            .shadow(4.dp)
            .fillMaxWidth()
            .background(Color.White)
            .navigationBarsPadding(),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        navigationBars.forEachIndexed { index, tabBarItem ->
            val isSelected = tabSelected == index
            AppNavigationBarItem(
                title = tabBarItem.title,
                iconResourceId = tabBarItem.iconResourceId,
                isSelected = isSelected,
            ) {
                onNavigateBarItemClick(index, tabBarItem)
            }
        }
    }
}

@Composable
fun RowScope.AppNavigationBarItem(
    title: String,
    iconResourceId: Int,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    AnimatedContentFadeIn(
        targetState = isSelected,
        modifier = Modifier.weight(1f),
        animationSpec = tween(300),
    ) { selected ->
        Column(
            modifier = Modifier
                .padding(top = 10.dp, bottom = 8.dp)
                .clickableNoneRipple(onClick),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = iconResourceId),
                contentDescription = title,
                tint = TextDescription,
                modifier = Modifier
                    .tintSelectStyle(selected)
                    .size(22.dp),
            )
            SpacerVertical(6.dp)
            Text(
                text = title,
                style = FontStyle.Bold.size(12.sp)
                    .takeIf { selected } ?: FontStyle.Regular.size(12.sp),
                lineHeight = 20.sp,
                modifier = Modifier.tintSelectStyle(selected),
                color = TextDescription,
            )
        }
    }
}