package com.asknilesh.composeanimations.animations

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ShapeChangeAnimation() {
  val infiniteTransition = rememberInfiniteTransition(label = "InfiniteTransition")
  val cornerPercent by infiniteTransition.animateValue(
    initialValue = 50,
    targetValue = 1,
    typeConverter = Int.VectorConverter,
    animationSpec = InfiniteRepeatableSpec(
      animation = keyframes {
        durationMillis = 2000
        0.at(100).with(FastOutSlowInEasing)
        0.at(400).with(FastOutSlowInEasing)
        25.at(800).with(FastOutSlowInEasing)
        50.at(1600).with(FastOutSlowInEasing)
        50.at(2000).with(FastOutSlowInEasing)
      },
      repeatMode = RepeatMode.Reverse
    ), label = "CornerPercent"
  )
  val angle by infiniteTransition.animateValue(
    initialValue = 00f,
    targetValue = 0f,
    typeConverter = Float.VectorConverter,
    animationSpec = InfiniteRepeatableSpec(
      animation = keyframes {
        durationMillis = 2000
        0f.at(100).with(FastOutSlowInEasing)
        0f.at(400).with(FastOutSlowInEasing)
        45f.at(800).with(FastOutSlowInEasing)
        90f.at(1600).with(FastOutSlowInEasing)
        90f.at(2000).with(FastOutSlowInEasing)
      },
      repeatMode = RepeatMode.Reverse
    ), label = "Angle"
  )

  Box(
    modifier = Modifier
      .clipToBounds()
      .padding(50.dp)
      .rotate(angle)
      .clip(RoundedCornerShape(cornerPercent))
      .border(
        width = 5.dp,
        color = Color(0xFF2196F3),
        shape = RoundedCornerShape(cornerPercent)
      )
  ) {
    Box(
      modifier = Modifier
        .width(150.dp)
        .height(150.dp)
        .background(Color(0xFFE91E63))
    )
  }
}