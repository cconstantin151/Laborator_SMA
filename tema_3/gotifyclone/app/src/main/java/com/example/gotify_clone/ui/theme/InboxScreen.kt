package com.example.gotify_clone.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.gotify_clone.di.Di
import java.util.Date

@Composable
fun InboxScreen() {
    val dao = Di.db.notificationDao()
    val items by dao.getAll().collectAsState(initial = emptyList())

    LazyColumn {
        items(items) { n ->
            ListItem(
                headlineContent = { Text("[${n.appId}] ${n.title}") },
                supportingContent = { Text(n.body) },
                overlineContent = { Text("P${n.priority} • ${Date(n.timestamp)}") }
            )
            Divider()
        }
    }
}
