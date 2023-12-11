package com.asknilesh.composeanimations.animations

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable fun AnimateWithDrawPath() {
  val heartPath = Path()

  val drawPathAnimation = remember {
    Animatable(0f)
  }
  val pathMeasure = remember {
    PathMeasure()
  }

  LaunchedEffect(key1 = Unit) {
    drawPathAnimation.animateTo(
      targetValue = 1f,
      animationSpec = InfiniteRepeatableSpec(
        repeatMode = RepeatMode.Restart, animation = tween(4000)
      ),
    )
  }
  val animatedPath = remember {
    derivedStateOf {
      val newPath = Path()
      pathMeasure.setPath(heartPath, false)
      pathMeasure.getSegment(
        startDistance = 0f,
        stopDistance = drawPathAnimation.value * pathMeasure.length,
        destination = newPath
      )
      newPath
    }
  }

  Column(verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally) {
    Canvas(modifier = Modifier.size(200.dp)) {
      heartPath.moveTo(0f, size.height / 3)

      heartPath.cubicTo(0f, 0f, size.width / 2, 0f, size.width / 2, size.height / 3)
      heartPath.cubicTo(size.width / 2, 0f, size.width, 0f, size.width, size.height / 3)

      heartPath.cubicTo(
        size.width, size.height * 2 / 3, size.width / 2, size.height, size.width / 2, size.height
      )
      heartPath.cubicTo(
        0f, size.height * 2 / 3, 0f, size.height / 2, 0f, size.height / 3
      )
      drawPath(color = Color.Blue, path = animatedPath.value, style = Stroke(20f))
    }

    Spacer(modifier = Modifier.height(20.dp))
    DrawStar()

  }
}

fun createStarPath(center: Offset, innerRadius: Float, outerRadius: Float): Path {
  val path = Path()

  // Number of points in the star
  val points = 5

  // Angle between each point in radians
  val angle = Math.PI / points

  // Start at the top point of the star
  val initialAngle = -Math.PI / 2
  var currentAngle = initialAngle

  path.moveTo(
    center.x + outerRadius * Math.cos(currentAngle).toFloat(),
    center.y + outerRadius * Math.sin(currentAngle).toFloat()
  )

  // Draw the outer points of the star
  for (i in 1 until 2 * points) {
    currentAngle += angle
    val radius = if (i % 2 == 0) outerRadius else innerRadius
    path.lineTo(
      center.x + radius * Math.cos(currentAngle).toFloat(),
      center.y + radius * Math.sin(currentAngle).toFloat()
    )
  }

  // Close the path
  path.close()

  return path
}

@Composable fun DrawStar() {
  var starPath = Path()

  val starDrawPathAnimation = remember {
    Animatable(0f)
  }
  val starPathMeasure = remember {
    PathMeasure()
  }

  LaunchedEffect(key1 = Unit) {
    starDrawPathAnimation.animateTo(
      targetValue = 1f,
      animationSpec = InfiniteRepeatableSpec(
        repeatMode = RepeatMode.Restart,
        animation = tween(3000)
      ),
    )
  }

  val animatedStarPath = remember {
    derivedStateOf {
      val newPath = Path()
      starPathMeasure.setPath(starPath, false)
      starPathMeasure.getSegment(
        startDistance = 0f,
        stopDistance = starDrawPathAnimation.value * starPathMeasure.length,
        destination = newPath
      )
      newPath
    }
  }

  Canvas(modifier = Modifier.size(500.dp)) {
    starPath = createStarPath(
      center = Offset(size.width / 2f, size.height / 2f),
      innerRadius = 50f,
      outerRadius = 100f
    )
    drawPath(color = Color.Red, path = animatedStarPath.value, style = Stroke(20f))
  }
}