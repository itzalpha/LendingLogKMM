package com.example.depressionapp.domain.note

import com.example.depressionapp.domain.time.DateTimeUtil


class SearchNotes {
    fun execute(reportNotes: List<ReportNote>, query: String): List<ReportNote> {
        if(query.isBlank()) {
            return reportNotes
        }
        return reportNotes.filter {
            it.title.trim().lowercase().contains(query.lowercase())

        }.sortedBy {
            DateTimeUtil.toEpochMillis(it.created)
        }
    }
}