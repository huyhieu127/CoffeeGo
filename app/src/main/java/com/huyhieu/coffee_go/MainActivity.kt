package com.huyhieu.coffee_go

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat.setDecorFitsSystemWindows
import androidx.navigation.compose.rememberNavController
import com.huyhieu.coffee_go.navigation.AppNavigation
import com.huyhieu.coffee_go.ui.theme.CoffeeGoTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            CoffeeGoTheme {
                AppNavigation(navController)
            }
        }
    }
}