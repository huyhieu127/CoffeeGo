package com.huyhieu.coffee_go.screens.order_detail

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.huyhieu.coffee_go.navigation.route.MainDest
import com.huyhieu.domain.entity.Coffee
import com.huyhieu.libs.toJson
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

private const val coffeeIdArg = "coffeeId"
private val URL_CHARACTER_ENCODING = UTF_8.name()

fun SavedStateHandle.coffeeIdArg(): String {
    return URLDecoder.decode(checkNotNull(this[coffeeIdArg]), URL_CHARACTER_ENCODING)
}

fun NavController.navigateToOrderDetail(coffee: Coffee) {
    val encodeId = URLEncoder.encode(coffee.id.toString(), URL_CHARACTER_ENCODING)
    val orderDetailDestination = MainDest.OrderDetail(
        coffeeId = coffee.idStr,
        coffeeJson = coffee.toJson(),
        orderId = 0,
    )
    this.navigate(orderDetailDestination) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.orderDetail(
    onCloseScreen: () -> Unit = {},
) {
    composable<MainDest.OrderDetail>(
        enterTransition = {
            fadeIn(
                animationSpec = tween(
                    300, easing = LinearEasing
                )
            ) + slideIntoContainer(
                animationSpec = tween(300, easing = EaseIn),
                towards = AnimatedContentTransitionScope.SlideDirection.Up
            )
        },
        exitTransition = {
            fadeOut(
                animationSpec = tween(
                    300, easing = LinearEasing
                )
            ) + slideOutOfContainer(
                animationSpec = tween(300, easing = EaseOut),
                towards = AnimatedContentTransitionScope.SlideDirection.Down
            )
        }
    ) {
        val orderDetailDestination = it.toRoute<MainDest.OrderDetail>()

        OrderDetailScreen(
            orderDetailDest = orderDetailDestination,
            onCloseScreen = onCloseScreen,
        )
    }
}

//
//fun NavController.navigateToOrderDetail(coffeeId: Int) {
//    val encodeId = URLEncoder.encode(coffeeId.toString(), URL_CHARACTER_ENCODING)
//    val route = MainRoute.OrderDetail.route.append("/$encodeId")
//    this.navigate(route) {
//        launchSingleTop = true
//    }
//}
//
//fun NavGraphBuilder.orderDetail() {
//    composable(
//        route = MainRoute.OrderDetail.route.append("/{$coffeeIdArg}"),
//        arguments = listOf(
//            navArgument(coffeeIdArg) { type = NavType.StringType },
//        )
//    ) {
//        val coffee = it.toRoute<Coffee>()
//        OrderDetailScreen()
//    }
//}
