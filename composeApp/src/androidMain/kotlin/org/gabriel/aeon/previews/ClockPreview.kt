package org.gabriel.aeon.previews

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.gabriel.aeon.views.clock.Clock

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ClockPreview(){
  MaterialTheme {
    Clock(
      modifier = Modifier.fillMaxSize()
    )
  }
}