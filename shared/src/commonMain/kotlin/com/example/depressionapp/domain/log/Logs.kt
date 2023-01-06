package com.example.depressionapp.domain.log

import kotlinx.datetime.LocalDateTime

data class Logs(
    val id: Long?,
    val name: String,
    val objectLent: String,
    val time: String,
    val created : LocalDateTime
)
{
}