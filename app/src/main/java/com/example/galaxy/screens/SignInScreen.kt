package com.example.galaxy.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.example.galaxy.R
import com.example.galaxy.authorization.signIn
import com.example.galaxy.models.User

@Composable
fun SingInScreen (
    user: User,
    navController: NavController,
    context: Context
) {
    Box {
        Image (
            painter = painterResource(id = R.drawable.home),
            contentDescription = "Welcome screen background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(Color(36, 54, 82, 230))
                .padding(top = 35.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row (
                modifier = Modifier.padding(start = 15.dp, end = 25.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton (
                    onClick = { navController.popBackStack("welcome", inclusive = false) },
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .size(30.dp)
                ) {
                    Icon (
                        Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "Return back button",
                        modifier = Modifier.size(30.dp),
                        tint = Color(255, 255, 255, 245)
                    )
                }
                Text (
                    text = "Login to Account",
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.quicksandbold)),
                    color = Color(255, 255, 255, 245)
                )
            }

            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 50.dp, bottom = 50.dp)
            ) {
                var email by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }

                Text (
                    text = "Galaxy",
                    fontSize = 45.sp,
                    fontFamily = FontFamily(Font(R.font.brunoaceregular)),
                    color = Color(255, 255, 255, 250),
                    modifier = Modifier.padding(bottom = 50.dp)
                )

                TextField (
                    value = email,
                    onValueChange = { newValue -> email = newValue },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier
                        .padding(start = 25.dp, end = 25.dp)
                        .fillMaxWidth()
                        .height(62.dp)
                        .clip(shape = RoundedCornerShape(20.dp))
                        .shadow(2.dp)
                        .background(Color(51, 73, 109, 255))
                        .padding(top = 2.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color(255, 255, 255, 230),
                        backgroundColor = Color(51, 73, 109, 255),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    placeholder = {
                        Text (
                            text = "Your email",
                            color = Color(255, 255, 255, 180),
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.quicksandsemibold))
                        )
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Email,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(start = 15.dp, top = 1.dp, bottom = 4.dp),
                            tint = Color(255, 255, 255, 180)
                        )
                    }
                )
                TextField (
                    value = password,
                    onValueChange = { newValue -> password = newValue },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier
                        .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                        .fillMaxWidth()
                        .height(62.dp)
                        .clip(shape = RoundedCornerShape(20.dp))
                        .shadow(2.dp)
                        .background(Color(51, 73, 109, 255))
                        .padding(top = 2.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color(255, 255, 255, 230),
                        backgroundColor = Color(51, 73, 109, 255),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    placeholder = {
                        Text(
                            text = "Your password",
                            color = Color(255, 255, 255, 180),
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.quicksandsemibold))
                        )
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Lock,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(start = 15.dp, top = 1.dp, bottom = 4.dp),
                            tint = Color(255, 255, 255, 180)
                        )
                    }
                )

                Divider(
                    color = Color(58, 83, 124, 255),
                    modifier = Modifier
                        .padding(top = 20.dp, start = 25.dp, end = 25.dp, bottom = 20.dp)
                )


                Button(
                    onClick = {
                        signIn(
                            email = email,
                            password = password,
                            user = user,
                            navController = navController,
                            context = context
                        )
                    },
                    modifier = Modifier
                        .padding(start = 25.dp, end = 25.dp)
                        .fillMaxWidth()
                        .height(66.dp)
                        .clip(shape = RoundedCornerShape(24.dp)),
                    colors = ButtonDefaults.buttonColors(Color(33, 99, 215, 255))
                ) {
                    Text(
                        text = "Sign in",
                        fontSize = 19.sp,
                        fontFamily = FontFamily(Font(R.font.quicksandbold)),
                        color = Color(255, 255, 255, 250)
                    )
                }
            }

            TextButton(
                onClick = { navController.navigate("sign-up") },
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Don’t have an account? ",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.quicksandsemibold)),
                    color = Color(255, 255, 255, 250)
                )
                Text(
                    text = "Sign up",
                    fontSize = 17.sp,
                    fontFamily = FontFamily(Font(R.font.quicksandbold)),
                    textDecoration = TextDecoration.Underline,
                    color = Color(37, 106, 224, 255)
                )
            }
        }
    }
}