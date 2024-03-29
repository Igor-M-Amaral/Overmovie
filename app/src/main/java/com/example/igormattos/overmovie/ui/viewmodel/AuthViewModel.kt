package com.example.igormattos.overmovie.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class AuthViewModel(private val firebaseAuth: FirebaseAuth) : ViewModel() {

    private val _verify = MutableLiveData<Boolean>()
    val verify: LiveData<Boolean> = _verify

    val message = MutableLiveData<String>()

    fun checkIfUserIsLogger() {
        if (firebaseAuth.currentUser != null) {
            _verify.postValue(true)
        } else {
            _verify.postValue(false)
        }
    }

    fun register(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                firebaseAuth.currentUser!!.sendEmailVerification()
                    .addOnCompleteListener { sendEmail ->
                        if (sendEmail.isSuccessful) {
                            _verify.postValue(true)
                            message.postValue("Check your email")
                        } else {
                            message.postValue(it.exception?.message ?: "")
                        }
                    }
            }
        }
    }

    fun login(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                _verify.postValue(true)
            } else {
                _verify.postValue(false)
                message.postValue(it.exception?.message ?: "")
            }
        }
    }

    fun loginWithGoogle(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                _verify.postValue(true)
            } else {
                _verify.postValue(false)
                message.postValue(it.exception.toString())
            }
        }
    }

    fun logout() {
        firebaseAuth.signOut()
    }

    fun forgetPassword(email: String) {

    }

    fun sendEmailVerification() {

    }
}