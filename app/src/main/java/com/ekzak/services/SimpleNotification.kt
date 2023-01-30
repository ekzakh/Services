package com.ekzak.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat

class SimpleNotification(private val context: Context) {

    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    private var id = 0

    fun show(title: String, text: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(text)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()
        notificationManager.notify(id++, notification)
    }

    companion object {
        private const val CHANNEL_ID = "channel_id"
        private const val CHANNEL_NAME = "channel_name"
    }
}
