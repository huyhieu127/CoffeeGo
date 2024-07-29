package com.huyhieu.coffee_go.screens.order_detail

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.huyhieu.coffee_go.navigation.route.MainDestination
import com.huyhieu.domain.entity.Coffee
import com.huyhieu.libs.toData
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
    val orderDetailDestination = MainDestination.OrderDetail(
        coffeeId = coffee.idStr,
        coffeeJson = coffee.toJson(),
    )
    this.navigate(orderDetailDestination) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.orderDetail(
    onCloseScreen: () -> Unit = {},
) {
    composable<MainDestination.OrderDetail> {
        val orderDetailDestination = it.toRoute<MainDestination.OrderDetail>()
        val coffee =
            orderDetailDestination.coffeeJson.toData<Coffee>() ?: Coffee(name = "Not found!")
        OrderDetailScreen(
            coffee = coffee,
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
