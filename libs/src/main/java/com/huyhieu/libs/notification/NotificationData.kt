package com.huyhieu.libs.notification

class NotificationData(
    val title: String = "",
    val content: String = "",
    val type: String = "",
    val imageUrl: String = "",
    val promotionId: String = ""
)

fun MutableMap<String, String>.toNotificationModel() = kotlin.runCatching {
    NotificationData(
        title = this["title"].orEmpty(),
        content = this["content"].orEmpty(),
        type = this["type"].orEmpty(),
        imageUrl = this["image_url"].orEmpty(),
        promotionId = this["promotionId"].orEmpty()
    )
}.getOrDefault(NotificationData())