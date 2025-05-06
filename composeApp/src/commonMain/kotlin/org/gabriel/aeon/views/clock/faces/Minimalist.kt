package org.gabriel.aeon.views.clock.faces

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin


fun DrawScope.drawMinimalistClockFace(center: Offset, radius: Float) {
  // Outline
  drawCircle(
    color = Color.LightGray,
    center = center,
    radius = radius,
    style = Stroke(width = 4f)
  )

  // Hour markers
  for (i in 0 until 12) {
    val angle = i * 30f - 90f
    val radians = angle * (PI / 180)
    val start = Offset(
      x = center.x + (radius - 20) * cos(radians).toFloat(),
      y = center.y + (radius - 20) * sin(radians).toFloat()
    )
    val end = Offset(
      x = center.x + radius * cos(radians).toFloat(),
      y = center.y + radius * sin(radians).toFloat()
    )
    drawLine(Color.Black, start, end, strokeWidth = 4f)
  }
}