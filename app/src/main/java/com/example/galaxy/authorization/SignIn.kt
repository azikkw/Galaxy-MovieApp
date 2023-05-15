package com.example.galaxy.authorization

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.example.galaxy.models.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

fun signIn (
    email: String,
    password: String,
    user: User,
    navController: NavController,
    context: Context
) {
    if(email.isEmpty() || password.isEmpty()) {
        Toast.makeText(context, "Fill all the fields!", Toast.LENGTH_SHORT).show()
        return
    }
    Firebase.auth.signInWithEmailAndPassword(email, password)
    .addOnSuccessListener { success ->
        Firebase.firestore.collection("users")
            .whereEqualTo("userId", success.user?.uid)
            .get()
            .addOnSuccessListener { createdUser ->
                for(cu in createdUser) {
                    user.userId = cu.get("userId").toString()
                    user.firstName = cu.get("firstName").toString()
                    user.lastName = cu.get("lastName").toString()
                    user.email = cu.get("email").toString()
                }
                navController.navigate("galaxy")
            }
    }
    .addOnFailureListener {
        Toast.makeText(context, "Check your email or password!", Toast.LENGTH_SHORT).show()
    }
}