package com.example.student_hacks.Custom_classes.Specific

import com.example.student_hacks.Custom_classes.Database.Database
import com.example.student_hacks.Custom_classes.Exceptions.SignInFailException
import com.example.student_hacks.Custom_classes.Exceptions.SignUpFailException
import com.example.student_hacks.Custom_classes.User.User
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class FirebaseDB : Database() {

    //Authentication related
    private var auth: FirebaseAuth

    //Store data on a database
    private var db : FirebaseFirestore

    private lateinit var user : User

    init {
        auth = Firebase.auth
        db = Firebase.firestore
        if (auth.currentUser != null) {
            user = User(
                id = auth.currentUser!!.uid,
                username = auth.currentUser!!.email!!
            )
        }
    }

    override fun signInDb(email: String, password: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            task ->
            if (!task.isSuccessful) {
                onFailure(SignInFailException())
            } else {
                onSuccess()
            }
        }
    }

    override fun signupDb(email: String, password: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            task ->
            if (!task.isSuccessful) {
                onFailure(SignUpFailException())
            } else {
                onSuccess()
            }
        }
    }

    override fun getFriendListDb(id: Int) {
        db.collection("profile")
            .document(id.toString())
            .get()
            .addOnCompleteListener {
                document ->
                if (document != null) {
                    //Get all friends data
                }
            }
    }

    override fun getCredentialDb(id: Int) {

    }


}