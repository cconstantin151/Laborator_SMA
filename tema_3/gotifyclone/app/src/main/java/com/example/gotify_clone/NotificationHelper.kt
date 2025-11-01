package com.example.gotify_clone

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class NotificationHelper(private val context: Context) {

    private fun ensureChannel(appId: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val chan = NotificationChannel(appId, "Channel $appId",
                NotificationManager.IMPORTANCE_DEFAULT)
            (context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
                .createNotificationChannel(chan)
        }
    }

    fun show(appId: String, priority: Int, title: String, body: String) {
        ensureChannel(appId)
        val pr = when {
            priority >= 5 -> NotificationCompat.PRIORITY_HIGH
            priority >= 4 -> NotificationCompat.PRIORITY_DEFAULT
            priority >= 3 -> NotificationCompat.PRIORITY_LOW
            else -> NotificationCompat.PRIORITY_MIN
        }
        val builder = NotificationCompat.Builder(context, appId)
            .setSmallIcon(android.R.drawable.stat_notify_more)
            .setContentTitle(title)
            .setContentText(body)
            .setStyle(NotificationCompat.BigTextStyle().bigText(body))
            .setPriority(pr)
            .setAutoCancel(true)

        NotificationManagerCompat.from(context)
            .notify((System.nanoTime() % Int.MAX_VALUE).toInt(), builder.build())
    }
}
