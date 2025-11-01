package com.example.gotify_clone.sms

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import com.example.gotify_clone.NotificationHelper
import com.example.gotify_clone.data.NotificationEntity
import com.example.gotify_clone.di.Di
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SmsReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION != intent.action) return

        val messages = Telephony.Sms.Intents.getMessagesFromIntent(intent)
        val msgBody = messages.joinToString("") { it.messageBody }

        // Expected: APPID|PRIORITY|TITLE|BODY
        val parts = msgBody.split("|", limit = 4)
        if (parts.size < 4) return

        val appId = parts[0].trim().ifEmpty { "default" }
        val priority = parts[1].toIntOrNull() ?: 3
        val title = parts[2].trim().ifEmpty { "Notification" }
        val body = parts[3].trim()

        NotificationHelper(context).show(appId, priority, title, body)

        CoroutineScope(Dispatchers.IO).launch {
            runCatching {
                Di.db.notificationDao().insert(
                    NotificationEntity(appId = appId, priority = priority, title = title, body = body)
                )
            }
        }
    }
}
