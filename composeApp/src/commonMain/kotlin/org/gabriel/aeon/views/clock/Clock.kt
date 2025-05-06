package org.gabriel.aeon.views.clock

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.drawscope.Stroke
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import kotlinx.datetime.toLocalDateTime
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import org.gabriel.aeon.views.clock.faces.drawRomanNumeralsClockFace

@Composable
fun Clock(
  modifier: Modifier,
) {

  var time by remember { mutableStateOf(currentLocalTime()) }
  val textMeasurer = rememberTextMeasurer()

  // Update every second
  LaunchedEffect(Unit) {
    while (true) {
      time = currentLocalTime()
      delay(1000L)
    }
  }

  Canvas(
    modifier = modifier,
    onDraw = {

      val center = Offset(size.width / 2, size.height / 2)
      val radius = size.minDimension / 2.2f

//      drawClockFace(
//        center, radius
//      )

      drawRomanNumeralsClockFace(center, radius, textMeasurer)

      // Calculate angles for hands
      val hourAngle = ((time.hour % 12) + time.minute / 60f) * 30f - 90f
      val minuteAngle = (time.minute + time.second / 60f) * 6f - 90f
      val secondAngle = time.second * 6f - 90f

      drawClockHand(
        center, hourAngle, radius * 0.5f,  Color.Black, 6f
      )

      drawClockHand(
        center, minuteAngle, radius * 0.75f,  Color.DarkGray, 6f
      )

      drawClockHand(
        center, secondAngle, radius * 0.9f,  Color.Red, 6f
      )

      // Center dot
      drawCircle(Color.Black, radius = 5f, center = center)
    }
  )
}

private fun DrawScope.drawClockHand(
  center: Offset,
  angleDegrees: Float,
  length: Float,
  color: Color,
  strokeWidth: Float
) {
  val radians = angleDegrees * (PI / 180)
  val end = Offset(
    x = center.x + length * cos(radians).toFloat(),
    y = center.y + length * sin(radians).toFloat()
  )
  drawLine(color, center, end, strokeWidth = strokeWidth)
}

