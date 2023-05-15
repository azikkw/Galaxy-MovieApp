package com.example.galaxy.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.galaxy.R

@Composable
fun ErrorScreen (
    retryAction: () -> Unit
) {
    Column (
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text (
            text = "Error caused!",
            color = Color(255, 255, 255, 250),
            fontSize = 22.sp,
            fontFamily = FontFamily(Font(R.font.quicksandsemibold))
        )
        Button(
            onClick = retryAction,
            modifier = Modifier
                .padding(top = 15.dp, start = 25.dp, end = 25.dp)
                .fillMaxWidth()
                .height(66.dp)
                .clip(shape = RoundedCornerShape(24.dp)),
            colors = ButtonDefaults.buttonColors(Color(33, 99, 215, 255))
        ) {
            Text(
                text = "Retry",
                fontSize = 19.sp,
                fontFamily = FontFamily(Font(R.font.quicksandbold)),
                color = Color(255, 255, 255, 250)
            )
        }
    }
}