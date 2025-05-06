package org.gabriel.aeon.views.clock

import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun currentLocalTime(): kotlinx.datetime.LocalTime {
  return kotlinx.datetime.Clock.System.now()
    .toLocalDateTime(TimeZone.currentSystemDefault())
    .time
}