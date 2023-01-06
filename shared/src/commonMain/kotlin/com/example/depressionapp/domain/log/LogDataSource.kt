package com.example.depressionapp.domain.log

interface LogDataSource {
    suspend fun insertLog(logs: Logs)
    suspend fun getLogById(id: Long): Logs?
    suspend fun getAllLogs(): List<Logs>
    suspend fun deleteLogById(id: Long)
}