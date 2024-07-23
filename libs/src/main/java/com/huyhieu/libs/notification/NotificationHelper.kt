package com.huyhieu.libs.notification

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.huyhieu.libs.R
import com.huyhieu.libs.logDebug
import com.huyhieu.libs.notification.NotificationHelper.Companion.CHANNEL_DESCRIPTION
import com.huyhieu.libs.notification.NotificationHelper.Companion.CHANNEL_ID
import com.huyhieu.libs.notification.NotificationHelper.Companion.CHANNEL_NAME
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class NotificationHelper(val context: Context) {

    private val notificationManager = NotificationManagerCompat.from(context)

    private val soundUri: Uri
        get() {
            //val soundUri = Settings.System.DEFAULT_NOTIFICATION_URI
            val soundUri =
                Uri.parse(("android.resource://" + context.packageName + "/" + R.raw.sound_notification))
            logDebug("showNotification: defaultSoundUri: $soundUri")
            return soundUri
        }

    fun showNotification(data: MutableMap<String, String>) {
        // Xử lý payload dữ liệu
        val notificationModel = data.toNotificationModel()
        pushNotification(notificationModel)
    }

    fun removeNotification(notificationId: Int) {
        notificationManager.cancel(notificationId)
    }

    private fun pushNotification(notificationData: NotificationData) {
        val builder = settingNotification(notificationData)
        notificationManager.also {
            it.settingChannelForSinceApi26(context, soundUri)// Available since API 26
            it.notify(context = context, id = 0, builder = builder)
        }
    }

    private fun settingNotification(notificationData: NotificationData?): NotificationCompat.Builder {
        val pendingIntent = PendingIntentFactory.direction(
            context = context,
            type = notificationData?.type.orEmpty(),
            data = notificationData?.promotionId.orEmpty()
        )
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification) // Biểu tượng đơn sắc
            .setContentTitle(notificationData?.title.orEmpty())
            .setContentText(notificationData?.content.orEmpty())
            .setContentIntent(pendingIntent)
            .setSound(soundUri)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
        runCatching {
            builder.loadLargeIcon(context, notificationData?.imageUrl.orEmpty())
        }
        return builder
    }


    companion object {
        internal const val CHANNEL_ID = "promotion_channel_id"
        internal const val CHANNEL_NAME = "Khuyến mãi"
        internal const val CHANNEL_DESCRIPTION = "Thông tin các siêu khuyến mãi"
    }
}

private fun NotificationCompat.Builder.loadLargeIcon(
    context: Context,
    imageUrl: String,
) {
    val bitmapDefault = BitmapFactory.decodeResource(context.resources, R.drawable.ic_car_red)
    if (imageUrl.isNotEmpty()) {
        runBlocking {
            val bitmap = runCatching { awaitBitmapNetwork() }.getOrNull() ?: bitmapDefault
            setLargeIcon(bitmap)
        }
    } else {
        setLargeIcon(bitmapDefault)
    }
}

private fun NotificationManagerCompat.notify(
    context: Context,
    id: Int,
    builder: NotificationCompat.Builder
) {
    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS)
        != PackageManager.PERMISSION_GRANTED
    ) {
        //Handle request permission here
        return
    }
    notify(0, builder.build())
}

private fun NotificationManagerCompat.settingChannelForSinceApi26(context: Context, soundUri: Uri) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {


        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance).apply {
            description = CHANNEL_DESCRIPTION
            enableLights(true)
            enableVibration(true)
            setSound(soundUri, myAudioAttributes)
        }
        this.createNotificationChannel(channel)
    }
}

private val myAudioAttributes
    get() = AudioAttributes.Builder()
        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
        .setUsage(AudioAttributes.USAGE_ALARM)
        .build()


private suspend fun awaitBitmapNetwork() =
    suspendCancellableCoroutine { coroutine ->
        GlideFake.await(
            onResourceReady = {
                coroutine.resume(it)
            },
            onLoadCleared = {
                coroutine.resume(null)
            }
        )
    }

object GlideFake {
    fun await(onResourceReady: (Bitmap?) -> Unit, onLoadCleared: (Bitmap?) -> Unit) {
        //onResourceReady(Any)
        //onLoadCleared(null)
    }
}