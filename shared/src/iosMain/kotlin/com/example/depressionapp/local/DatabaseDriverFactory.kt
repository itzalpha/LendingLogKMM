package com.example.depressionapp.local


import com.example.depressionapp.database.LogDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(LogDatabase.Schema, "log.db")
    }
}