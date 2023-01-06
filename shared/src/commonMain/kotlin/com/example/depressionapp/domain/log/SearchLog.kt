package com.example.depressionapp.domain.log

import com.example.depressionapp.domain.time.DateTimeUtil

class SearchLog {

    fun execute(logs: List<Logs>, query: String): List<Logs> {
        if(query.isBlank()) {
            return logs
        }

        return logs.filter {
            it.name.trim().lowercase().contains(query.lowercase()) || it.objectLent.trim().lowercase().contains(query.lowercase())

        }.sortedBy {
            DateTimeUtil.toEpochMillis(it.created)
        }
    }
}