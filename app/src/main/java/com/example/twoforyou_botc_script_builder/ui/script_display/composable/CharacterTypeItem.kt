package com.example.twoforyou_botc_script_builder.ui.script_display.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun CharacterTypeItem(
    text: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text
        )
    }


}