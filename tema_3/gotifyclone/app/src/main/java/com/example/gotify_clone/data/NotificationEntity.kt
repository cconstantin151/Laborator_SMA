package com.example.gotify_clone.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notifications")
data class NotificationEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val appId: String,
    val priority: Int,
    val title: String,
    val body: String,
    val timestamp: Long = System.currentTimeMillis()
)
