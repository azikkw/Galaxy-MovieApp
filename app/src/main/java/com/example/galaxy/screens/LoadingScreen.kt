package com.example.galaxy.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.galaxy.R

@Composable
fun LoadingScreen() {
    Box (
        modifier = Modifier.fillMaxWidth().wrapContentWidth(align = Alignment.CenterHorizontally)
    ) {
        Text (
            text = "Loading ...",
            color = Color(255, 255, 255, 250),
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.quicksandbold)),
        )
    }
}