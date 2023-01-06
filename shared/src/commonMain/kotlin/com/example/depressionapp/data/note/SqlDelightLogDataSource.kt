package com.example.depressionapp.data.note

import com.example.depressionapp.database.LogDatabase
import com.example.depressionapp.domain.log.Logs
import com.example.depressionapp.domain.log.LogDataSource
import com.example.depressionapp.domain.time.DateTimeUtil

class SqlDelightLogDataSource(db: LogDatabase): LogDataSource {

    private val queries = db.noteQueries

    override suspend fun insertLog(logs: Logs) {
        queries.insertLog(
            id = logs.id,
            name = logs.name,
            objectLent = logs.objectLent,
            time = logs.time,
            created = DateTimeUtil.toEpochMillis(logs.created)
        )
    }

    override suspend fun getLogById(id: Long): Logs? {
        return queries
            .getLogById(id)
            .executeAsOneOrNull()
            ?.toNote()
    }

    override suspend fun getAllLogs(): List<Logs> {
        return queries
            .getAllLogs()
            .executeAsList()
            .map { it.toNote() }
    }

    override suspend fun deleteLogById(id: Long) {
        queries.deleteLogById(id)
    }
}