package com.swengineering.everyportal.utils.interfaces

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.util.*

interface TimeStamp {
    fun timeStampToString(createdAtDate: Date): String {
        val createdAt = LocalDateTime.ofInstant(createdAtDate.toInstant(), ZoneId.systemDefault())
        val now = LocalDateTime.now()
        return when {
            ChronoUnit.YEARS.between(createdAt, now) > 0 -> "${
                ChronoUnit.YEARS.between(
                    createdAt,
                    now
                )
            }년 전"
            ChronoUnit.MONTHS.between(createdAt, now) > 0 -> "${
                ChronoUnit.MONTHS.between(
                    createdAt,
                    now
                )
            }달 전"
            ChronoUnit.WEEKS.between(createdAt, now) > 0 -> "${
                ChronoUnit.WEEKS.between(
                    createdAt,
                    now
                )
            }주 전"
            ChronoUnit.DAYS.between(createdAt, now) > 0 -> "${
                ChronoUnit.DAYS.between(
                    createdAt,
                    now
                )
            }일 전"
            ChronoUnit.HOURS.between(createdAt, now) > 0 -> "${
                ChronoUnit.HOURS.between(
                    createdAt,
                    now
                )
            }시간 전"
            ChronoUnit.MINUTES.between(createdAt, now) > 0 -> "${
                ChronoUnit.MINUTES.between(
                    createdAt,
                    now
                )
            }분 전"
            else -> "${ChronoUnit.SECONDS.between(createdAt, now)}초 전"
        }
    }
}
