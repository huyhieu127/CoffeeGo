package com.huyhieu.coffee_go.presentation.order_detail

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.huyhieu.coffee_go.navigation.route.MainDest
import com.huyhieu.domain.entity.Order
import com.huyhieu.libs.toJson

fun NavController.navigateToOrderDetail(coffeeId: Int, order: Order?) {
    val orderDetailDestination = MainDest.OrderDetail(
        coffeeId = coffeeId,
        order = order.toJson(),
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
