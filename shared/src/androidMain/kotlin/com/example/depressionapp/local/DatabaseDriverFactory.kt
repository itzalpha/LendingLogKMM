package com.example.depressionapp.local

import android.content.Context
import com.example.depressionapp.database.LogDatabase

import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(LogDatabase.Schema, context, "note.db")
    }
}