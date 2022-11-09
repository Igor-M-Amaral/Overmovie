package com.example.igormattos.overmovie.data.repository

import com.google.firebase.auth.FirebaseAuth

class AuthRepository {

    private val firebaseAuth = FirebaseAuth.getInstance()

    suspend fun register(email: String, userName: String, password: String): Boolean {
        val result = firebaseAuth.createUserWithEmailAndPassword("igu_mattos@hotmail.com", "123456")
        if (result.isSuccessful) {
            val verification = firebaseAuth.currentUser!!.sendEmailVerification()
            return verification.isSuccessful
        }
        return false
    }


    suspend fun login(email: String, password: String): Boolean {
        val result = firebaseAuth.signInWithEmailAndPassword(email, password)
        return result.isSuccessful
    }

    suspend fun forgetPassword(email: String): Boolean {
        val result = firebaseAuth.sendPasswordResetEmail(email)
        return result.isSuccessful
    }
}



