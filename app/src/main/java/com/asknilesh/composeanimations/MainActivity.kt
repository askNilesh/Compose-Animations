package com.asknilesh.composeanimations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.asknilesh.composeanimations.Animations.AnimationWithDrawPath
import com.asknilesh.composeanimations.Animations.ShakeAnimation
import com.asknilesh.composeanimations.animations.AnimateWithDrawPath
import com.asknilesh.composeanimations.animations.ShakeAnimations
import com.asknilesh.composeanimations.animations.ShapeChangeAnimation
import com.asknilesh.composeanimations.ui.theme.ComposeAnimationsTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      ComposeAnimationsTheme {
        var selectedAnimation by remember { mutableStateOf<Animations?>(null) }

        Column(modifier = Modifier.fillMaxSize()) {
          DropdownMenuBox { animations ->
            selectedAnimation = animations
          }
          Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            when (selectedAnimation) {
              ShakeAnimation -> ShakeAnimations()
              Animations.ShapeChangeAnimation -> ShapeChangeAnimation()
              Animations.AnimationWithDrawPath -> AnimateWithDrawPath()
              else -> Text(text = "Please Select Animation")
            }
          }
        }
      }
    }
  }
}

enum class Animations(val animationName: String) {
  ShakeAnimation("Shake Animation"),
  ShapeChangeAnimation("Shape Change Animation"),
  AnimationWithDrawPath("Animation With DrawPath")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenuBox(
  onItemSelected: (Animations) -> Unit
) {
  val context = LocalContext.current
  val items = listOf<Animations>(
    ShakeAnimation,
    Animations.ShapeChangeAnimation,
    AnimationWithDrawPath
  )
  var expanded by remember { mutableStateOf(false) }
  var selectedText by remember { mutableStateOf<Animations?>(null) }

  Box(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp)
  ) {
    ExposedDropdownMenuBox(
      expanded = expanded,
      onExpandedChange = {
        expanded = !expanded
      }
    ) {
      TextField(
        value = selectedText?.animationName ?: "Select Animation",
        onValueChange = {},
        readOnly = true,
        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
        modifier = Modifier.fillMaxWidth().menuAnchor(),
        enabled = false
      )

      ExposedDropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
      ) {
        items.forEach { item ->
          DropdownMenuItem(
            text = { Text(text = item.animationName) },
            onClick = {
              onItemSelected.invoke(item)
              selectedText = item
              expanded = false
            }
          )
        }
      }
    }
  }
}