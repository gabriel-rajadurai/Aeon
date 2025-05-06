package org.gabriel.aeon.views.clock.faces

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.unit.sp
import org.gabriel.aeon.views.clock.currentLocalTime
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin


fun DrawScope.drawRomanNumeralsClockFace(center: Offset, radius: Float, textMeasurer: TextMeasurer) {

  val numerals = listOf(
    "XII", "I", "II", "III", "IV", "V", "VI",
    "VII", "VIII", "IX", "X", "XI",
  )

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

    // Calculate position for the hour number
    val textRadius = radius - 40  // Position the text slightly inside the edge
    val textX = center.x + textRadius * cos(radians).toFloat()
    val textY = center.y + textRadius * sin(radians).toFloat()

    // Create the hour number as an AnnotatedString
    val hourText = numerals[i]
    val annotatedString = AnnotatedString(hourText)

    // Use a TextMeasurer to create the layout

    val textLayoutResult = textMeasurer.measure(annotatedString, style = TextStyle(
      fontSize = 16.sp
    )
    )

    val hourColor = if(i == currentLocalTime().hour % 12) Color.Red else Color.Black

    // Draw the text using drawText
    drawText(
      textLayoutResult,
      brush = SolidColor(hourColor), // Set the brush (color)
      topLeft = Offset(textX - textLayoutResult.size.width / 2, textY - textLayoutResult.size.height / 2) // Centering the text
    )
  }
}
