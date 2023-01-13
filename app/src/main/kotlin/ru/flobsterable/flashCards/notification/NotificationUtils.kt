package ru.flobsterable.flashCards.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

fun createNotificationChannel(
    appContext: Context,
    channelId: String,
    name: String,
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val notificationChannel = NotificationChannel(
            channelId,
            name,
            NotificationManager.IMPORTANCE_DEFAULT
        )

        with(NotificationManagerCompat.from(appContext)) {
            createNotificationChannel(notificationChannel)
        }
    }
}

fun processNotificationBuilder(
    appContext: Context,
    channelId: String,
    title: String,
    text: String,
    icon: Int
) = NotificationCompat.Builder(appContext, channelId)
    .setContentText(text)
    .setContentTitle(title)
    .setSmallIcon(icon)
    .setOngoing(true)
    .setProgress(0, 0, true)
    .build()
