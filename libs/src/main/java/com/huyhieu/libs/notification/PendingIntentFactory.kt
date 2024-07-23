package com.huyhieu.libs.notification

import android.app.Activity
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
object PendingIntentFactory {
    fun direction(context: Context, type: String, data: String): PendingIntent {
        return when (type) {
            //"1" -> CloudMessagingDirection().direction(context, data)
            else -> DefaultDirection().direction(context, data)
        }
    }
}

interface PendingIntentDirection {
    fun direction(context: Context, data: String): PendingIntent
}

class DefaultDirection : PendingIntentDirection {
    override fun direction(context: Context, data: String): PendingIntent {
        //val intent = Intent(context, MainActivity::class.java).apply {
        val intent = Intent(context, Activity::class.java).apply {//change Activity -> MainActivity
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    }
}

//class CloudMessagingDirection : PendingIntentDirection {
//    override fun direction(context: Context, data: String): PendingIntent {
//        val intent = NavDeepLinkBuilder(context)
//            .setGraph(R.navigation.main_graph)
//            .setDestination(R.id.cloudMessagingFragment)
//            .setArguments(
//                Bundle().apply {
//                    putString("data", data)
//                }
//            )
//            .createPendingIntent()
//        return intent
//    }
//}