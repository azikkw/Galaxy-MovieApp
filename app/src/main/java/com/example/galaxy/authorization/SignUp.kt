package com.example.galaxy.authorization

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

fun signUp (
    firstName: String,
    lastName: String,
    email: String,
    password: String,
    navController: NavController,
    context: Context
) {
    if(firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
        Toast.makeText(context, "Fill all the fields!", Toast.LENGTH_SHORT).show()
        return
    }
    else if(password.length < 8) {
        Toast.makeText(context, "The password must be more than 8 characters long!", Toast.LENGTH_SHORT).show()
        return
    }

    Firebase.auth.createUserWithEmailAndPassword(email, password)
    .addOnSuccessListener { success ->
        val user = hashMapOf (
            "userId" to success.user?.uid,
            "firstName" to firstName,
            "lastName" to lastName,
            "email" to email,
            "password" to password
        )
        Firebase.firestore.collection("users")
            .add(user)
            .addOnSuccessListener {
                navController.navigate("sign-in")
            }
    }
}