package com.example.galaxy.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.galaxy.R

@Composable
fun WelcomeScreen (
    navController: NavController
) {
    Box {
        Image (
            painter = painterResource(id = R.drawable.welcome),
            contentDescription = "Welcome screen background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 85.dp, bottom = 15.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column (
                modifier = Modifier.padding(start = 25.dp, end = 25.dp)
            ) {
                Text (
                    text = "Galaxy",
                    fontSize = 50.sp,
                    fontFamily = FontFamily(Font(R.font.brunoaceregular)),
                    color = Color(255, 255, 255, 250),
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Text (
                    text = "Discover a whole new galaxy of movies with Galaxy - The movie app that brings you a universe of entertainment, anytime, anywhere!",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.quicksandsemibold)),
                    color = Color(255, 255, 255, 210)
                )
            }

            Column {
                Button (
                    onClick = { navController.navigate("sign-up") },
                    modifier = Modifier
                        .padding(start = 25.dp, end = 25.dp)
                        .fillMaxWidth()
                        .height(72.dp)
                        .clip(shape = RoundedCornerShape(36.dp)),
                    colors = ButtonDefaults.buttonColors(Color(33, 99, 215, 255))
                ) {
                    Text (
                        text = "Dive in now",
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.quicksandbold)),
                        color = Color(255, 247, 237),
                        modifier = Modifier.padding(end = 3.dp)
                    )
                    Icon (
                        Icons.Filled.KeyboardArrowRight,
                        contentDescription = "Dive in now icon",
                        Modifier
                            .padding(top = 4.dp, start = 2.dp)
                            .size(33.dp),
                        tint = Color.White
                    )
                }

                TextButton (
                    onClick = { navController.navigate("sign-in")},
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text (
                        text = "Already have an account? ",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.quicksandsemibold)),
                        color = Color.White
                    )
                    Text (
                        text ="Sign in",
                        fontSize = 17.sp,
                        fontFamily = FontFamily(Font(R.font.quicksandbold)),
                        textDecoration = TextDecoration.Underline,
                        color = Color(0, 228, 140)
                    )
                }
            }
        }
    }
}