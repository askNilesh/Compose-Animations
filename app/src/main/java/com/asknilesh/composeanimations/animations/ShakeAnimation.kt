package com.asknilesh.composeanimations.animations

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun ShakeAnimations() {
  Column(modifier = Modifier
    .fillMaxSize()
    .padding(10.dp)) {

    val infiniteTransition = rememberInfiniteTransition(label = "Infinite Transition")

    val scaleInfinite by infiniteTransition.animateFloat(
      initialValue = 1f,
      targetValue = .85f,
      animationSpec = infiniteRepeatable(
        animation = tween(500, easing = LinearEasing),
        repeatMode = RepeatMode.Reverse
      ),
      label = "Scale Infinite Animation"
    )

    val rotation by infiniteTransition.animateFloat(
      initialValue = -10f,
      targetValue = 10f,
      animationSpec = infiniteRepeatable(
        animation = tween(700, easing = LinearEasing),
        repeatMode = RepeatMode.Reverse
      ),
      label = "Rotation"
    )

    Icon(
      imageVector = Icons.Rounded.Notifications,
      contentDescription = null,
      tint = Color.White,
      modifier = Modifier
        .graphicsLayer {
          scaleX = scaleInfinite
          scaleY = scaleInfinite
        }
        .background(Color.Black, CircleShape)
        .size(50.dp)
        .padding(10.dp)
    )
    Spacer(modifier = Modifier.height(40.dp))
    Text(text = "Shake Animation with Rotation")
    Icon(
      imageVector = Icons.Rounded.Settings,
      contentDescription = null,
      tint = Color.White,
      modifier = Modifier
        .graphicsLayer {
          scaleX = scaleInfinite
          scaleY = scaleInfinite
          rotationZ = rotation
        }
        .background(Color.Black, CircleShape)
        .size(50.dp)
        .padding(10.dp)
    )

  }
}