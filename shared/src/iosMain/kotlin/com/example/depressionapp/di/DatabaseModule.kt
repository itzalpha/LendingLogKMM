package com.example.depressionapp.di

import com.example.depressionapp.data.note.SqlDelightLogDataSource
import com.example.depressionapp.database.LogDatabase
import com.example.depressionapp.domain.log.LogDataSource
import com.example.depressionapp.local.DatabaseDriverFactory


class DatabaseModule {

    private val factory by lazy { DatabaseDriverFactory() }
    val logDataSource: LogDataSource by lazy {
        SqlDelightLogDataSource(LogDatabase(factory.createDriver()))
    }
}