package com.huyhieu.coffee_go.screens.home

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.huyhieu.coffee_go.ui.common.button.clickableNoneRipple
import com.huyhieu.coffee_go.ui.theme.actionBarSize
import com.huyhieu.coffee_go.ui.theme.edgeSize
import com.huyhieu.coffee_go.ui.theme.utils.type.FontStyle
import com.huyhieu.coffee_go.ui.theme.utils.type.size

private val TAG = "HomeScreen"

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeVM: HomeVM = hiltViewModel(),
) {
    val context = LocalContext.current
    val homeUiState = homeVM.homeUiState.collectAsState().value
    HomeUi(
        modifier = modifier,
        toolbar = homeUiState.toolbar,
        actionAvatarClick = {
        },
        actionNotificationClick = {
            Toast.makeText(context, "Notification", Toast.LENGTH_SHORT).show()
            homeVM.actionNotification()
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun HomeUi(
    modifier: Modifier = Modifier,
    toolbar: Toolbar = Toolbar(),
    actionAvatarClick: () -> Unit = {},
    actionNotificationClick: () -> Unit = {},
) {
    Scaffold(modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            ToolBar(
                toolbar = toolbar,
                actionAvatarClick = actionAvatarClick,
                actionNotificationClick = actionNotificationClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ToolBar(
    toolbar: Toolbar = Toolbar(
        greetings = "Good morning!", name = "Cristiano Ronaldo", isBadgeVisible = true
    ),
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