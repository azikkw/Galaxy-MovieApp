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
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.galaxy.R
import com.example.galaxy.authorization.signUp

@Composable
fun SignUpScreen (
    navController: NavController,
    context: Context
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
                .padding(top = 30.dp),
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
                    text = "Fill Your Profile",
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.quicksandbold)),
                    color = Color(255, 255, 255, 245)
                )
            }

            Column(modifier = Modifier.padding(top = 15.dp, start = 25.dp, end = 25.dp)) {
                Text (
                    text = "First name",
                    color = Color(255, 255, 255, 250),
                    fontSize = 19.sp,
                    fontFamily = FontFamily(Font(R.font.quicksandbold)),
                    modifier = Modifier.padding(bottom = 15.dp)
                )
                TextField (
                    value = firstName,
                    onValueChange = { newValue -> firstName = newValue },
                    modifier = Modifier
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
                            text = "Your first name",
                            color = Color(255, 255, 255, 180),
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.quicksandsemibold))
                        )
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Person,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(start = 15.dp, top = 1.dp, bottom = 4.dp),
                            tint = Color(255, 255, 255, 180)
                        )
                    }
                )
            }
            Column(modifier = Modifier.padding(top = 5.dp, start = 25.dp, end = 25.dp)) {
                Text (
                    text = "Last name",
                    color = Color(255, 255, 255, 250),
                    fontSize = 19.sp,
                    fontFamily = FontFamily(Font(R.font.quicksandbold)),
                    modifier = Modifier.padding(bottom = 15.dp)
                )
                TextField (
                    value = lastName,
                    onValueChange = { newValue -> lastName = newValue },
                    modifier = Modifier
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
                            text = "Your last name",
                            color = Color(255, 255, 255, 180),
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.quicksandsemibold))
                        )
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Person,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(start = 15.dp, top = 1.dp, bottom = 4.dp),
                            tint = Color(255, 255, 255, 180)
                        )
                    }
                )
            }
            Column(modifier = Modifier.padding(top = 5.dp, start = 25.dp, end = 25.dp)) {
                Text (
                    text = "Email",
                    color = Color(255, 255, 255, 250),
                    fontSize = 19.sp,
                    fontFamily = FontFamily(Font(R.font.quicksandbold)),
                    modifier = Modifier.padding(bottom = 15.dp)
                )
                TextField (
                    value = email,
                    onValueChange = { newValue -> email = newValue },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier
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
            }
            Column(modifier = Modifier.padding(top = 5.dp, start = 25.dp, end = 25.dp)) {
                Text (
                    text = "Password",
                    color = Color(255, 255, 255, 250),
                    fontSize = 19.sp,
                    fontFamily = FontFamily(Font(R.font.quicksandbold)),
                    modifier = Modifier.padding(bottom = 15.dp)
                )
                TextField (
                    value = password,
                    onValueChange = { newValue -> password = newValue },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier
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
            }

            Button (
                onClick = {
                    signUp (
                        firstName = firstName,
                        lastName = lastName,
                        email = email,
                        password = password,
                        navController = navController,
                        context = context
                    )
                },
                modifier = Modifier
                    .padding(top = 30.dp, start = 25.dp, end = 25.dp)
                    .fillMaxWidth()
                    .height(66.dp)
                    .clip(shape = RoundedCornerShape(24.dp)),
                colors = ButtonDefaults.buttonColors(Color(33, 99, 215, 255))) {
                Text (
                    text = "Sign up",
                    fontSize = 19.sp,
                    fontFamily = FontFamily(Font(R.font.quicksandbold)),
                    color = Color(255, 255, 255, 250)
                )
            }
            TextButton(
                onClick = { navController.navigate("sign-in") },
                modifier = Modifier
                    .padding(bottom = 15.dp)
                    .fillMaxWidth()
            ) {
                Text (
                    text = "Already have an account? ",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.quicksandsemibold)),
                    color = Color(255, 255, 255, 250)
                )
                Text (
                    text ="Sign in",
                    fontSize = 17.sp,
                    fontFamily = FontFamily(Font(R.font.quicksandbold)),
                    textDecoration = TextDecoration.Underline,
                    color = Color(37, 106, 224, 255)
                )
            }
        }
    }
}