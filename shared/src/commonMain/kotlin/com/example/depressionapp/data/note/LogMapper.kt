package com.example.depressionapp.data.note

import com.example.depressionapp.domain.log.Logs
import database.LogEntity
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun LogEntity.toNote(): Logs {
    return Logs(
        id = id,
        name = name,
        objectLent = objectLent,
        time = time,
        created = Instant
            .fromEpochMilliseconds(created)
            .toLocalDateTime(TimeZone.currentSystemDefault())
    )
}