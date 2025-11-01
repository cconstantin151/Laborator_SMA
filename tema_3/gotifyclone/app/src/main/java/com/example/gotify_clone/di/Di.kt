package com.example.gotify_clone.di

import android.content.Context
import androidx.room.Room
import com.example.gotify_clone.data.AppDatabase

object Di {
    lateinit var db: AppDatabase
    fun init(context: Context) {
        if (!::db.isInitialized) {
            db = Room.databaseBuilder(context, AppDatabase::class.java, "ntify.db").build()
        }
    }
}
