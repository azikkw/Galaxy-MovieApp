package com.example.galaxy.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.galaxy.models.User
import com.example.galaxy.R

@Composable
fun ProfileScreen (
    user: User,
    navController: NavController
) {
    Box {
        Image (
            painter = painterResource(id = R.drawable.home),
            contentDescription = "Home screen background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(Color(36, 54, 82, 240))
                .padding(top = 30.dp)
        ) {
            Row (
                modifier = Modifier
                    .padding(start = 25.dp, end = 25.dp, bottom = 35.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text (
                    text = "Galaxy",
                    fontSize = 32.sp,
                    fontFamily = FontFamily(Font(R.font.brunoaceregular)),
                    color = Color(255, 255, 255, 250),
                )
                IconButton (
                    onClick = { navController.navigate("search") },
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                        .clip(shape = RoundedCornerShape(14.dp))
                        .background(Color(212, 95, 113, 255))
                ) {
                    Icon (
                        Icons.Default.Search,
                        contentDescription = "Search",
                        Modifier.size(25.dp),
                        tint = Color.White
                    )
                }
            }

            Text (
                text = "Welcome, " + user.firstName,
                color = Color(255, 255, 255, 250),
                fontSize = 38.sp,
                fontFamily = FontFamily(Font(R.font.quicksandbold)),
                modifier = Modifier.padding(top = 20.dp, start = 25.dp, end = 25.dp, bottom = 0.dp)
            )

            Row (
                modifier = Modifier.padding(top = 40.dp, start = 25.dp, end = 25.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "User ID:",
                    color = Color(255, 255, 255, 190),
                    fontSize = 17.sp,
                    fontFamily = FontFamily(Font(R.font.quicksandsemibold)),
                    modifier = Modifier.padding(end = 10.dp)
                )
                Text(
                    text = user.userId,
                    color = Color(255, 255, 255, 250),
                    fontSize = 17.sp,
                    fontFamily = FontFamily(Font(R.font.quicksandbold)),
                    textAlign = TextAlign.End
                )
            }
            Divider(
                color = Color(58, 83, 124, 255),
                modifier = Modifier.padding(top = 15.dp, start = 25.dp, end = 25.dp, bottom = 15.dp)
            )

            Row(
                modifier = Modifier.padding(start = 25.dp, end = 25.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "First name:",
                    color = Color(255, 255, 255, 190),
                    fontSize = 17.sp,
                    fontFamily = FontFamily(Font(R.font.quicksandsemibold))
                )
                Text(
                    text = user.firstName,
                    color = Color(255, 255, 255, 250),
                    fontSize = 17.sp,
                    fontFamily = FontFamily(Font(R.font.quicksandbold))
                )
            }
            Divider(
                color = Color(58, 83, 124, 255),
                modifier = Modifier.padding(top = 15.dp, start = 25.dp, end = 25.dp, bottom = 15.dp)
            )

            Row(
                modifier = Modifier.padding(start = 25.dp, end = 25.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Last name:",
                    color = Color(255, 255, 255, 190),
                    fontSize = 17.sp,
                    fontFamily = FontFamily(Font(R.font.quicksandsemibold))
                )
                Text(
                    text = user.lastName,
                    color = Color(255, 255, 255, 250),
                    fontSize = 17.sp,
                    fontFamily = FontFamily(Font(R.font.quicksandbold))
                )
            }
            Divider(
                color = Color(58, 83, 124, 255),
                modifier = Modifier.padding(top = 15.dp, start = 25.dp, end = 25.dp, bottom = 15.dp)
            )

            Row(
                modifier = Modifier.padding(start = 25.dp, end = 25.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Email:",
                    color = Color(255, 255, 255, 190),
                    fontSize = 17.sp,
                    fontFamily = FontFamily(Font(R.font.quicksandsemibold))
                )
                Text(
                    text = user.email,
                    color = Color(255, 255, 255, 250),
                    fontSize = 17.sp,
                    fontFamily = FontFamily(Font(R.font.quicksandbold))
                )
            }

            Button(
                onClick = { navController.popBackStack("sign-in", inclusive = false) },
                modifier = Modifier
                    .padding(top = 50.dp, start = 25.dp, end = 25.dp)
                    .fillMaxWidth()
                    .height(68.dp)
                    .clip(shape = RoundedCornerShape(24.dp)),
                colors = ButtonDefaults.buttonColors(Color(33, 99, 215, 255))
            ) {
                Text(
                    text = "Exit",
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.quicksandbold)),
                    color = Color(255, 255, 255, 250)
                )
            }
        }
    }
}